package kg.founders.bff.config.settings.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import kg.founders.core.exceptions.BadRequestException;
import kg.founders.core.util.ConsumerE;
import kg.founders.core.util.GsonIgnore;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class GsonAdapterFactory implements TypeAdapterFactory, ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        Class<? super T> rawType = type.getRawType();
        if (rawType.isEnum()) {
            //noinspection unchecked,rawtypes
            return new EnumTypeAdapter(rawType);
        }

        return null;
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public static class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {

        final Class<T> type;

        public EnumTypeAdapter(Class<?> type) {
            //noinspection unchecked
            this.type = (Class<T>) type;
        }

        private static <T> boolean writeWithType(Class<T> type, Object o, ConsumerE<T, IOException> writer) throws IOException {
            if (type == o.getClass()) {
                //noinspection unchecked
                writer.accept((T) o);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void write(JsonWriter out, T value) throws IOException {
            if (value == null || !value.getClass().isEnum()) {
                out.nullValue();
                return;
            }

            try {
                out.beginObject();
                out.name("value");
                out.value(value.toString());
                Arrays.stream(Introspector.getBeanInfo(value.getClass()).getPropertyDescriptors())
                        .filter(pd -> pd.getReadMethod() != null && !"class".equals(pd.getName()) && !"declaringClass".equals(pd.getName()))
                        .forEach(pd -> {
                            try {
                                var shouldIgnore = false;

                                try { shouldIgnore = value.getClass().getDeclaredField(pd.getName()).getDeclaredAnnotation(GsonIgnore.class) != null;} catch (NoSuchFieldException ignored){}

                                var param = pd.getReadMethod().invoke(value);

                                if (param != null && !shouldIgnore) {
                                    out.name(pd.getName());
                                    if (param.getClass().isEnum()) {
                                        //noinspection unchecked
                                        write(out, (T) param);
                                    } else if (!(
                                            writeWithType(Boolean.class, param, out::value) ||
                                            writeWithType(Character.class, param, out::value) ||
                                            writeWithType(Byte.class, param, out::value) ||
                                            writeWithType(Short.class, param, out::value) ||
                                            writeWithType(Integer.class, param, out::value) ||
                                            writeWithType(Long.class, param, out::value) ||
                                            writeWithType(Float.class, param, out::value) ||
                                            writeWithType(Double.class, param, out::value) ||
                                            writeWithType(String.class, param, out::value)
                                    )) {
                                        out.nullValue();
                                    }
                                }
                            } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                                log.error("", e);
                            }
                        });
                out.endObject();
            } catch (IntrospectionException e) {
                log.error("", e);
            }
        }

        public T read(JsonReader in) throws IOException {
            switch (in.peek()) {
                case NULL: {
                    in.nextNull();
                    return null;
                }
                case BEGIN_OBJECT: {
                    in.beginObject();

                    T r = null;

                    while (in.hasNext()) {
                        var name = in.nextName();
                        if (name.equals("value")) {
                            var value = in.nextString();
                            try {
                                r = Enum.valueOf(type, value);
                            } catch (IllegalStateException e) {
                                throw new BadRequestException(e.getMessage());
                            }
                        } else {
                            in.skipValue();
                        }
                    }

                    in.endObject();

                    return r;
                }
                case STRING: {
                    return Enum.valueOf(type, in.nextString());
                }
                default:
                    return null;
            }
        }
    }
}
