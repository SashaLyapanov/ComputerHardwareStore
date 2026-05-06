package h2o.tz.lyapanov.tz_lyapanov_aa.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HardDriveResponseDTO extends DeviceResponseDTO {
    private int volume;
}
