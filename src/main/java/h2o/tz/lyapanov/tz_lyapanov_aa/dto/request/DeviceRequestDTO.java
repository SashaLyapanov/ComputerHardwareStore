package h2o.tz.lyapanov.tz_lyapanov_aa.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceRequestDTO {

    private long serialNumber;
    private String manufacturer;
    private BigDecimal price;
    private int stockQuantity;

}
