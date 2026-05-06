package h2o.tz.lyapanov.tz_lyapanov_aa.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.enums.LaptopSize;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LaptopResponseDTO extends DeviceResponseDTO {
    private LaptopSize size;
}
