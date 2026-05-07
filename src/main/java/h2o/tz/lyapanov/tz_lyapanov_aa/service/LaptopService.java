package h2o.tz.lyapanov.tz_lyapanov_aa.service;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.LaptopRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.LaptopResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.exceptions.DeviceNotFoundException;
import h2o.tz.lyapanov.tz_lyapanov_aa.mapper.LaptopMapper;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.Laptop;
import h2o.tz.lyapanov.tz_lyapanov_aa.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopService implements DeviceCRUDService<LaptopRequestDTO, LaptopResponseDTO> {

    @Autowired
    private LaptopRepository laptopRepository;
    @Autowired
    private LaptopMapper laptopMapper;

    @Override
    public LaptopResponseDTO getDevice(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return laptopMapper.laptopResponseDTOFromLaptop(laptopRepository.findById(id).orElse(null));
    }

    @Override
    public List<LaptopResponseDTO> getAllDevices() {
        return laptopMapper.laptopResponseDTOListFromLaptopList(laptopRepository.findAll());
    }

    @Override
    public LaptopResponseDTO createDevice(LaptopRequestDTO laptopRequestDTO) {
        Laptop laptop = laptopMapper.laptopFromLaptopRequestDTO(laptopRequestDTO);
        return laptopMapper.laptopResponseDTOFromLaptop(laptopRepository.save(laptop));
    }

    @Override
    public LaptopResponseDTO updateDevice(String id, LaptopRequestDTO laptopRequestDTO) {
        Laptop laptop = laptopRepository.findById(id).orElseThrow(
                () -> new DeviceNotFoundException("Laptop with id " + id + " not found")
        );
        laptop.setSerialNumber(laptopRequestDTO.getSerialNumber());
        laptop.setManufacturer(laptopRequestDTO.getManufacturer());
        laptop.setPrice(laptopRequestDTO.getPrice());
        laptop.setStockQuantity(laptopRequestDTO.getStockQuantity());
        laptop.setSize(laptopRequestDTO.getSize());
        return laptopMapper.laptopResponseDTOFromLaptop(laptopRepository.save(laptop));
    }
}
