package h2o.tz.lyapanov.tz_lyapanov_aa.service;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.HardDriveRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.HardDriveResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.exceptions.DeviceNotFoundException;
import h2o.tz.lyapanov.tz_lyapanov_aa.mapper.HardDriveMapper;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.HardDrive;
import h2o.tz.lyapanov.tz_lyapanov_aa.repository.HardDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardDriveService implements DeviceCRUDService<HardDriveRequestDTO, HardDriveResponseDTO> {
    @Autowired
    private HardDriveRepository hardDriveRepository;
    @Autowired
    private HardDriveMapper hardDriveMapper;


    @Override
    public HardDriveResponseDTO getDevice(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        } else {
            return hardDriveMapper.hardDriveResponseDTOFromHardDrive(hardDriveRepository.findById(id).orElse(null));
        }
    }

    @Override
    public List<HardDriveResponseDTO> getAllDevices() {
        return hardDriveMapper.hardDriveResponseDTOListFromHardDriveList(hardDriveRepository.findAll());
    }

    @Override
    public HardDriveResponseDTO createDevice(HardDriveRequestDTO hardDriveRequestDTO) {
        HardDrive hardDrive = hardDriveMapper.hardDriveFromHardDriveRequestDTO(hardDriveRequestDTO);
        return hardDriveMapper.hardDriveResponseDTOFromHardDrive(hardDriveRepository.save(hardDrive));
    }

    @Override
    public HardDriveResponseDTO updateDevice(String id, HardDriveRequestDTO hardDriveRequestDTO) {
        HardDrive hardDrive = hardDriveRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("HardDrive with id " + id + " not found."));
        hardDrive.setSerialNumber(hardDriveRequestDTO.getSerialNumber());
        hardDrive.setManufacturer(hardDriveRequestDTO.getManufacturer());
        hardDrive.setPrice(hardDriveRequestDTO.getPrice());
        hardDrive.setStockQuantity(hardDriveRequestDTO.getStockQuantity());
        hardDrive.setVolume(hardDriveRequestDTO.getVolume());
        return hardDriveMapper.hardDriveResponseDTOFromHardDrive(hardDriveRepository.save(hardDrive));
    }
}
