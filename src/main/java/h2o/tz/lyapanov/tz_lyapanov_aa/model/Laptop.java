package h2o.tz.lyapanov.tz_lyapanov_aa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.enums.LaptopSize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "laptops")
public class Laptop extends Device {

    //размер
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private LaptopSize size;
}
