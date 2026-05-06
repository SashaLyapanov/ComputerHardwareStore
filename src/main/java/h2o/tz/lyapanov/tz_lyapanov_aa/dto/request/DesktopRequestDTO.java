package h2o.tz.lyapanov.tz_lyapanov_aa.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.enums.DesktopType;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DesktopRequestDTO extends DeviceRequestDTO {
    private DesktopType formFactor;
}
