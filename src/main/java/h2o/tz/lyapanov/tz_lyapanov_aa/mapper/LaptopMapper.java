package h2o.tz.lyapanov.tz_lyapanov_aa.mapper;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.LaptopRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.LaptopResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.Laptop;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LaptopMapper {

    LaptopRequestDTO laptopReqDTOFromLaptop(Laptop laptop);
    Laptop laptopFromLaptopRequestDTO(LaptopRequestDTO dto);

    List<LaptopRequestDTO> laptopReqDTOListFromLaptopList(List<Laptop> laptops);
    List<Laptop> laptopListFromRequestDTOList(List<LaptopRequestDTO> requestDTOList);

    LaptopResponseDTO laptopResponseDTOFromLaptop(Laptop laptop);
    Laptop laptopFromLaptopResponseDTO(LaptopResponseDTO dto);

    List<LaptopResponseDTO> laptopResponseDTOListFromLaptopList(List<Laptop> laptops);
    List<Laptop> laptopListFromResponseDTOList(List<LaptopResponseDTO> responseDTOList);

}
