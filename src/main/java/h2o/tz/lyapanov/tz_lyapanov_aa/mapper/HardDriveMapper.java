package h2o.tz.lyapanov.tz_lyapanov_aa.mapper;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.HardDriveRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.HardDriveResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.HardDrive;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HardDriveMapper {

    HardDriveRequestDTO hardDriveReqDTOFromHardDrive(HardDrive hardDrive);
    HardDrive hardDriveFromHardDriveRequestDTO(HardDriveRequestDTO dto);

    List<HardDriveRequestDTO> hardDriveReqDTOListFromHardDriveList(List<HardDrive> hardDrives);
    List<HardDrive> hardDriveListFromRequestDTOList(List<HardDriveRequestDTO> requestDTOList);

    HardDriveResponseDTO hardDriveResponseDTOFromHardDrive(HardDrive hardDrive);
    HardDrive hardDriveFromHardDriveResponseDTO(HardDriveResponseDTO dto);

    List<HardDriveResponseDTO> hardDriveResponseDTOListFromHardDriveList(List<HardDrive> hardDrives);
    List<HardDrive> hardDriveListFromResponseDTOList(List<HardDriveResponseDTO> responseDTOList);

}
