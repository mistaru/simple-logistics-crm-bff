package kg.founders.bff.config.settings.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Base64;

public class ByteArrayDeserializer implements JsonDeserializer<byte[]> {

    @Override
    public byte[] deserialize(
            JsonElement jsonElement,
            Type type,
            JsonDeserializationContext jsonDeserializationContext
    ) throws JsonParseException {
        return Base64.getDecoder().decode(jsonElement.getAsString());
    }
}
