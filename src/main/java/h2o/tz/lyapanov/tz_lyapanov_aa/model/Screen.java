package h2o.tz.lyapanov.tz_lyapanov_aa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "screens")
public class Screen extends Device{

    //диагональ
    @NotNull
    @Min(value = 1, message = "Incorrect value for screen diagonal")
    @Column(name = "diagonal", nullable = false)
    private float diagonal;

}
