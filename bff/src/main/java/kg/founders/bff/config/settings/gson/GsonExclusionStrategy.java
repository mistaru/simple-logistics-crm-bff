package kg.founders.bff.config.settings.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import kg.founders.core.util.GsonIgnore;

public class GsonExclusionStrategy implements ExclusionStrategy {

    private static final GsonExclusionStrategy INSTANCE = new GsonExclusionStrategy();

    public static GsonExclusionStrategy getInstance() {
        return INSTANCE;
    }

    private GsonExclusionStrategy() {}

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getAnnotation(GsonIgnore.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return aClass.getAnnotation(GsonIgnore.class) != null;
    }
}
