package h2o.tz.lyapanov.tz_lyapanov_aa.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class Device extends GenericEntity {
    //номер серии
    @NotNull
    @Column(name = "serial_number", nullable = false)
    private long serialNumber;

    //производитель
    @NotBlank
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    //цена
    @NotNull
    @Positive
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    //количество на складе
    @NotNull
    @PositiveOrZero
    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;
}
