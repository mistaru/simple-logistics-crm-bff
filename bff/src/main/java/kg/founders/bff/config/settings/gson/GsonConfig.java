package kg.founders.bff.config.settings.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;
import kg.founders.core.util.DateFormatUtil;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Component
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class GsonConfig {

    GsonAdapterFactory gsonAdapterFactory;

    @Bean
    public Gson gsonBean() {
        return new GsonBuilder()
                .setDateFormat(DateFormatUtil.DATE_TIME_FORMAT)
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(byte[].class, new ByteArrayDeserializer())
                .registerTypeAdapterFactory(new HibernateProxyAdapterFactory())
                .registerTypeAdapter(Json.class, (JsonSerializer<Json>) (src, typeOfSrc, context) -> new JsonParser().parse(src.value()))
                .registerTypeAdapterFactory(gsonAdapterFactory)
                .setExclusionStrategies(GsonExclusionStrategy.getInstance())
                .create();
    }
}
