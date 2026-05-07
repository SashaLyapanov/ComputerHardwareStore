package h2o.tz.lyapanov.tz_lyapanov_aa.mapper;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.ScreenRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.ScreenResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.Screen;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScreenMapper {

    ScreenRequestDTO screenReqDTOFromScreen(Screen screen);
    Screen screenFromScreenRequestDTO(ScreenRequestDTO dto);

    List<ScreenRequestDTO> screenReqDTOListFromScreenList(List<Screen> screens);
    List<Screen> screenListFromRequestDTOList(List<ScreenRequestDTO> requestDTOList);

    ScreenResponseDTO screenResponseDTOFromScreen(Screen screen);
    Screen screenFromScreenResponseDTO(ScreenResponseDTO dto);

    List<ScreenResponseDTO> screenResponseDTOListFromScreenList(List<Screen> screens);
    List<Screen> screenListFromResponseDTOList(List<ScreenResponseDTO> responseDTOList);

}
