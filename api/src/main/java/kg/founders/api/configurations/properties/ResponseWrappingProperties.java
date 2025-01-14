package kg.founders.api.configurations.properties;

import kg.founders.common.models.wrapper.ResponseWrapperModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "app.response-wrapping")
public class ResponseWrappingProperties {
    boolean enabled;
    boolean enableResultLogging;
    String wrapperClassName;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Class<? extends ResponseWrapperModel> getWrapperClass() throws ClassNotFoundException {
        return (wrapperClassName == null || wrapperClassName.isEmpty()) ? null
                : (Class<? extends ResponseWrapperModel>) Class.forName(wrapperClassName);
    }
}
