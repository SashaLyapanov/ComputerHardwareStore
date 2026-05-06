package h2o.tz.lyapanov.tz_lyapanov_aa.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.enums.DesktopType;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DesktopResponseDTO extends DeviceResponseDTO {
    private DesktopType formFactor;
}
