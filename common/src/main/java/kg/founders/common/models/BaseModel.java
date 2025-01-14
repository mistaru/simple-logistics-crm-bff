package kg.founders.common.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseModel {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
