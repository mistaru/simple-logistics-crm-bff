package kg.founders.core.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class SetCargoPriceRequest {
    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private Long cargoId;
}
