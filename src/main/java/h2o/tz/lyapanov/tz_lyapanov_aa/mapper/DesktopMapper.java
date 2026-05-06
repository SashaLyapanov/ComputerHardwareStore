package h2o.tz.lyapanov.tz_lyapanov_aa.mapper;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.DesktopRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.DesktopResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.Desktop;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DesktopMapper {

    DesktopRequestDTO desktopReqDTOFromDesktop(Desktop desktop);
    Desktop desktopFromDesktopRequestDTO(DesktopRequestDTO dto);

    List<DesktopRequestDTO> desktopReqDTOListFromDesktopList(List<Desktop> desktops);
    List<Desktop> desktopListFromRequestDTOList(List<DesktopRequestDTO> requestDTOList);

    DesktopResponseDTO desktopResponseDTOFromDesktop(Desktop desktop);
    Desktop desktopFromDesktopResponseDTO(DesktopResponseDTO dto);

    List<DesktopResponseDTO> desktopResponseDTOListFromDesktopList(List<Desktop> desktops);
    List<Desktop> desktopListFromResponseDTOList(List<DesktopResponseDTO> responseDTOList);

}
