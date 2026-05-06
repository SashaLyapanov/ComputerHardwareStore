package h2o.tz.lyapanov.tz_lyapanov_aa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.enums.DesktopType;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "desktops")
public class Desktop extends Device {

    //форм-фактор
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "form_factor", nullable = false)
    private DesktopType formFactor;

}
