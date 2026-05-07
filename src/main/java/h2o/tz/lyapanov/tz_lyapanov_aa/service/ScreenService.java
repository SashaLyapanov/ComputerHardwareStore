package h2o.tz.lyapanov.tz_lyapanov_aa.service;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.ScreenRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.ScreenResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.exceptions.DeviceNotFoundException;
import h2o.tz.lyapanov.tz_lyapanov_aa.mapper.ScreenMapper;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.Screen;
import h2o.tz.lyapanov.tz_lyapanov_aa.repository.ScreenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScreenService implements DeviceCRUDService<ScreenRequestDTO, ScreenResponseDTO> {

    private final ScreenRepository screenRepository;
    private final ScreenMapper screenMapper;

    @Override
    public ScreenResponseDTO getDevice(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return screenMapper.screenResponseDTOFromScreen(screenRepository.findById(id).orElse(null));
    }

    @Override
    public List<ScreenResponseDTO> getAllDevices() {
        return screenMapper.screenResponseDTOListFromScreenList(screenRepository.findAll());
    }

    @Override
    public ScreenResponseDTO createDevice(ScreenRequestDTO screenRequestDTO) {
        Screen screen = screenMapper.screenFromScreenRequestDTO(screenRequestDTO);
        return screenMapper.screenResponseDTOFromScreen(screenRepository.save(screen));
    }

    @Override
    public ScreenResponseDTO updateDevice(String id, ScreenRequestDTO screenRequestDTO) {
        Screen screen = screenRepository.findById(id).orElseThrow(
                () -> new DeviceNotFoundException("Screen with id " + id + " not found")
        );
        screen.setSerialNumber(screenRequestDTO.getSerialNumber());
        screen.setManufacturer(screenRequestDTO.getManufacturer());
        screen.setPrice(screenRequestDTO.getPrice());
        screen.setStockQuantity(screenRequestDTO.getStockQuantity());
        screen.setDiagonal(screenRequestDTO.getDiagonal());
        return screenMapper.screenResponseDTOFromScreen(screenRepository.save(screen));
    }
}
