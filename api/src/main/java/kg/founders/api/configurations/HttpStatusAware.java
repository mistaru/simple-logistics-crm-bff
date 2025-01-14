package kg.founders.api.configurations;

import org.springframework.http.HttpStatus;

public interface HttpStatusAware {
    HttpStatus getStatus();
}
