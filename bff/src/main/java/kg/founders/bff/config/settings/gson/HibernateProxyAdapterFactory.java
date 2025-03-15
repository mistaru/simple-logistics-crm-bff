package kg.founders.bff.config.settings.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.io.IOException;

public class HibernateProxyAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        //noinspection unchecked
        return (HibernateProxy.class.isAssignableFrom(type.getRawType()) ?
                (TypeAdapter<T>) new ProxyTypeAdapter(gson) : null);
    }

    public static class ProxyTypeAdapter extends TypeAdapter<HibernateProxy> {

        final Gson gson;

        ProxyTypeAdapter(Gson gson) {
            this.gson = gson;
        }

        @Override
        public HibernateProxy read(JsonReader in) {
            throw new UnsupportedOperationException("Not supported");
        }

        @Override
        public void write(JsonWriter out, HibernateProxy value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }

            Class<?> baseType = Hibernate.getClass(value);
            //noinspection rawtypes
            TypeAdapter delegate = gson.getAdapter(TypeToken.get(baseType));
            Object impl = value.getHibernateLazyInitializer().getImplementation();
            //noinspection unchecked
            delegate.write(out, impl);
        }
    }
}
