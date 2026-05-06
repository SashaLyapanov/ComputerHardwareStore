package h2o.tz.lyapanov.tz_lyapanov_aa.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreenRequestDTO extends DeviceRequestDTO {
    private float diagonal;
}
