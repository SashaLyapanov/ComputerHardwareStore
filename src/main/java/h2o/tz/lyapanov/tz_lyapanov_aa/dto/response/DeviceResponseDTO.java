package h2o.tz.lyapanov.tz_lyapanov_aa.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceResponseDTO {
    private String id;
    private long serialNumber;
    private String manufacturer;
    private BigDecimal price;
    private int stockQuantity;
}
