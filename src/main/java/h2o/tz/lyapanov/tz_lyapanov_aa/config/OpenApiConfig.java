package h2o.tz.lyapanov.tz_lyapanov_aa.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Computer store API",
                version = "1.0.0",
                description = "REST API for managing computer hardware store. " +
                    "This application supports desktops, laptops, screens and hard drives.",
                contact = @Contact(
                        name = "ComputerHardwareStore",
                        url = "https://github.com/SashaLyapanov/ComputerHardwareStore.git"
                )
        )
)
public class OpenApiConfig {
}
