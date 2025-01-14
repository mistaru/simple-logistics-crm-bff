package kg.founders.api.configurations;

import kg.founders.api.configurations.properties.ResponseWrappingProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("kg.founders.api")
@EnableConfigurationProperties({ResponseWrappingProperties.class})
public class ResponseWrappingConfiguration {
    @Bean
    public ResponseWrappingDefaultExceptionHandler defaultControllerExceptionHandler() {
        return new ResponseWrappingDefaultExceptionHandler();
    }
}
