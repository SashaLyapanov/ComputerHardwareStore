package h2o.tz.lyapanov.tz_lyapanov_aa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@NonNull
@Table(name = "hard_drives")
public class HardDrive extends Device {

    //объем диска в Гб
    @NotNull
    @Positive
    @Column(name = "volume", nullable = false)
    private int volume;
}
