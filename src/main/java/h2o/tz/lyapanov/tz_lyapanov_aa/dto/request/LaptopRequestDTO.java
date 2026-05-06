package h2o.tz.lyapanov.tz_lyapanov_aa.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.enums.LaptopSize;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LaptopRequestDTO extends DeviceRequestDTO {
    private LaptopSize size;
}
