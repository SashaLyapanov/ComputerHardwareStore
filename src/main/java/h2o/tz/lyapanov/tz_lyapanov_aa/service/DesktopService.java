package h2o.tz.lyapanov.tz_lyapanov_aa.service;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.DesktopRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.DesktopResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.exceptions.DeviceNotFoundException;
import h2o.tz.lyapanov.tz_lyapanov_aa.mapper.DesktopMapper;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.Desktop;
import h2o.tz.lyapanov.tz_lyapanov_aa.repository.DesktopRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DesktopService implements DeviceCRUDService<DesktopRequestDTO, DesktopResponseDTO> {

    private final DesktopRepository desktopRepository;
    private final DesktopMapper desktopMapper;

    public DesktopResponseDTO getDevice(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        } else {
            return desktopMapper.desktopResponseDTOFromDesktop(desktopRepository.findById(id).orElse(null));
        }
    }

    public List<DesktopResponseDTO> getAllDevices() {
        return desktopMapper.desktopResponseDTOListFromDesktopList(desktopRepository.findAll());
    }

    @Transactional
    public DesktopResponseDTO createDevice(DesktopRequestDTO desktopRequestDTO) {
        Desktop desktop = desktopMapper.desktopFromDesktopRequestDTO(desktopRequestDTO);
        return desktopMapper.desktopResponseDTOFromDesktop(desktopRepository.save(desktop));
    }

    @Transactional
    public DesktopResponseDTO updateDevice(String id, DesktopRequestDTO desktopRequestDTO) {
        Desktop desktop = desktopRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Desktop with id " + id + " not found."));
        desktop.setSerialNumber(desktopRequestDTO.getSerialNumber());
        desktop.setManufacturer(desktopRequestDTO.getManufacturer());
        desktop.setPrice(desktopRequestDTO.getPrice());
        desktop.setStockQuantity(desktopRequestDTO.getStockQuantity());
        desktop.setFormFactor(desktopRequestDTO.getFormFactor());
        return desktopMapper.desktopResponseDTOFromDesktop(desktopRepository.save(desktop));
    }
}
