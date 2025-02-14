package kg.founders.bff.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Method;

@Slf4j
public class EnumSerializer extends JsonSerializer<Enum> {

    @Override
    public void serialize(Enum anEnum, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("value", anEnum.name());

        try {
            Method descMethod = anEnum.getClass().getMethod("getDescription");
            Object description = descMethod.invoke(anEnum);
            jsonGenerator.writeStringField("description", description != null ?
                    description.toString() : "");
        } catch (NoSuchMethodException e) {
            log.warn("Method getDescription not found for enum type: {}", anEnum.getClass().getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        jsonGenerator.writeEndObject();
    }
}
