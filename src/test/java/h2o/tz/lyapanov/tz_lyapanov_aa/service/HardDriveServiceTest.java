package h2o.tz.lyapanov.tz_lyapanov_aa.service;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.HardDriveRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.HardDriveResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.exceptions.DeviceNotFoundException;
import h2o.tz.lyapanov.tz_lyapanov_aa.mapper.HardDriveMapper;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.HardDrive;
import h2o.tz.lyapanov.tz_lyapanov_aa.repository.HardDriveRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HardDriveServiceTest {
    @Mock
    private HardDriveRepository hardDriveRepository;

    @Mock
    private HardDriveMapper hardDriveMapper;

    @InjectMocks
    private HardDriveService hardDriveService;

    @Test
    void getDevice_success() {
        String id = "1";

        HardDrive hardDrive = createHardDrive();
        HardDriveResponseDTO responseDTO = createHardDriveResponseDTO();

        when(hardDriveRepository.findById(id)).thenReturn(Optional.of(hardDrive));
        when(hardDriveMapper.hardDriveResponseDTOFromHardDrive(hardDrive)).thenReturn(responseDTO);

        HardDriveResponseDTO result = hardDriveService.getDevice(id);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(responseDTO);

        Mockito.verify(hardDriveRepository, times(1)).findById(id);
        Mockito.verify(hardDriveMapper, times(1)).hardDriveResponseDTOFromHardDrive(hardDrive);
    }

    @Test
    void getDevice_nullId_returnNull() {
        HardDriveResponseDTO result = hardDriveService.getDevice(null);

        assertNull(result);

        verifyNoInteractions(hardDriveRepository);
        verifyNoInteractions(hardDriveMapper);
    }

    @Test
    void getDevice_emptyId_returnNull() {
        HardDriveResponseDTO result = hardDriveService.getDevice("");

        assertNull(result);

        verifyNoInteractions(hardDriveRepository);
        verifyNoInteractions(hardDriveMapper);
    }

    @Test
    void getDevice_notFound_returnNull() {
        String id = "999";

        when(hardDriveRepository.findById(id)).thenReturn(Optional.empty());
        when(hardDriveMapper.hardDriveResponseDTOFromHardDrive(null)).thenReturn(null);

        HardDriveResponseDTO result = hardDriveService.getDevice(id);

        assertNull(result);

        verify(hardDriveRepository, times(1)).findById(id);
        verify(hardDriveMapper, times(1)).hardDriveResponseDTOFromHardDrive(null);
    }

    @Test
    void getAllDevices_success() {
        HardDrive hardDrive1 = createHardDrive();
        HardDrive hardDrive2 = createSecondHardDrive();

        HardDriveResponseDTO responseDTO1 = createHardDriveResponseDTO();
        HardDriveResponseDTO responseDTO2 = createSecondHardDriveResponseDTO();

        List<HardDrive> hardDrives = List.of(hardDrive1, hardDrive2);
        List<HardDriveResponseDTO> responseDTOList = List.of(responseDTO1, responseDTO2);

        when(hardDriveRepository.findAll()).thenReturn(hardDrives);
        when(hardDriveMapper.hardDriveResponseDTOListFromHardDriveList(hardDrives)).thenReturn(responseDTOList);

        List<HardDriveResponseDTO> result = hardDriveService.getAllDevices();

        assertThat(result).isNotNull();
        assert (result.size() == 2);
        assertThat(result).isEqualTo(responseDTOList);

        verify(hardDriveRepository, times(1)).findAll();
        verify(hardDriveMapper, times(1)).hardDriveResponseDTOListFromHardDriveList(hardDrives);
    }

    @Test
    void createDevice_success() {
        HardDriveRequestDTO requestDTO = createHardDriveRequestDTO();
        HardDrive hardDrive = createHardDrive();
        HardDrive savedHardDrive = createHardDrive();
        HardDriveResponseDTO responseDTO = createHardDriveResponseDTO();

        when(hardDriveMapper.hardDriveFromHardDriveRequestDTO(requestDTO)).thenReturn(hardDrive);
        when(hardDriveRepository.save(hardDrive)).thenReturn(savedHardDrive);
        when(hardDriveMapper.hardDriveResponseDTOFromHardDrive(savedHardDrive)).thenReturn(responseDTO);

        HardDriveResponseDTO result = hardDriveService.createDevice(requestDTO);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(responseDTO);

        verify(hardDriveMapper, times(1)).hardDriveFromHardDriveRequestDTO(requestDTO);
        verify(hardDriveRepository, times(1)).save(hardDrive);
        verify(hardDriveMapper, times(1)).hardDriveResponseDTOFromHardDrive(savedHardDrive);
    }

    @Test
    void updateDevice_success() {
        String id = "1";

        HardDriveRequestDTO requestDTO = createHardDriveRequestDTO();
        HardDrive existingHardDrive = createHardDrive();
        HardDrive updatedHardDrive = createUpdatedHardDrive();
        HardDriveResponseDTO responseDTO = createUpdatedHardDriveResponseDTO();

        when(hardDriveRepository.findById(id)).thenReturn(Optional.of(existingHardDrive));
        when(hardDriveRepository.save(existingHardDrive)).thenReturn(updatedHardDrive);
        when(hardDriveMapper.hardDriveResponseDTOFromHardDrive(updatedHardDrive)).thenReturn(responseDTO);

        HardDriveResponseDTO result = hardDriveService.updateDevice(id, requestDTO);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(responseDTO);

        assertThat(existingHardDrive.getSerialNumber()).isEqualTo(requestDTO.getSerialNumber());
        assertThat(existingHardDrive.getManufacturer()).isEqualTo(requestDTO.getManufacturer());
        assertThat(existingHardDrive.getPrice()).isEqualTo(requestDTO.getPrice());
        assertThat(existingHardDrive.getStockQuantity()).isEqualTo(requestDTO.getStockQuantity());
        assertThat(existingHardDrive.getVolume()).isEqualTo(requestDTO.getVolume());

        verify(hardDriveRepository, times(1)).findById(id);
        verify(hardDriveRepository, times(1)).save(existingHardDrive);
        verify(hardDriveMapper, times(1)).hardDriveResponseDTOFromHardDrive(updatedHardDrive);
    }

    @Test
    void updateDevice_notFound_throwException() {
        String id = "999";
        HardDriveRequestDTO requestDTO = createHardDriveRequestDTO();

        when(hardDriveRepository.findById(id)).thenReturn(Optional.empty());

        DeviceNotFoundException exception = assertThrows(
                DeviceNotFoundException.class,
                () -> hardDriveService.updateDevice(id, requestDTO)
        );

        assertThat(exception.getMessage())
                .isEqualTo("HardDrive with id " + id + " not found.");

        verify(hardDriveRepository, times(1)).findById(id);
        verify(hardDriveRepository, never()).save(any());
        verifyNoInteractions(hardDriveMapper);
    }

    private HardDriveRequestDTO createHardDriveRequestDTO() {
        HardDriveRequestDTO dto = new HardDriveRequestDTO();
        dto.setSerialNumber(001);
        dto.setManufacturer("Seagate");
        dto.setPrice(new BigDecimal("69.99"));
        dto.setStockQuantity(30);
        dto.setVolume(1024);
        return dto;
    }

    private HardDrive createHardDrive() {
        HardDrive hardDrive = new HardDrive();
        hardDrive.setSerialNumber(001);
        hardDrive.setManufacturer("Seagate");
        hardDrive.setPrice(new BigDecimal("69.99"));
        hardDrive.setStockQuantity(30);
        hardDrive.setVolume(1024);
        return hardDrive;
    }

    private HardDrive createSecondHardDrive() {
        HardDrive hardDrive = new HardDrive();
        hardDrive.setSerialNumber(002);
        hardDrive.setManufacturer("Western Digital");
        hardDrive.setPrice(new BigDecimal("119.90"));
        hardDrive.setStockQuantity(18);
        hardDrive.setVolume(2048);
        return hardDrive;
    }

    private HardDrive createUpdatedHardDrive() {
        HardDrive hardDrive = new HardDrive();
        hardDrive.setSerialNumber(001);
        hardDrive.setManufacturer("Seagate");
        hardDrive.setPrice(new BigDecimal("69.99"));
        hardDrive.setStockQuantity(30);
        hardDrive.setVolume(1024);
        return hardDrive;
    }

    private HardDriveResponseDTO createHardDriveResponseDTO() {
        HardDriveResponseDTO dto = new HardDriveResponseDTO();
        dto.setId("1");
        dto.setSerialNumber(001);
        dto.setManufacturer("Seagate");
        dto.setPrice(new BigDecimal("69.99"));
        dto.setStockQuantity(30);
        dto.setVolume(1024);
        return dto;
    }

    private HardDriveResponseDTO createSecondHardDriveResponseDTO() {
        HardDriveResponseDTO dto = new HardDriveResponseDTO();
        dto.setId("2");
        dto.setSerialNumber(002);
        dto.setManufacturer("Western Digital");
        dto.setPrice(new BigDecimal("119.90"));
        dto.setStockQuantity(18);
        dto.setVolume(2048);
        return dto;
    }

    private HardDriveResponseDTO createUpdatedHardDriveResponseDTO() {
        HardDriveResponseDTO dto = new HardDriveResponseDTO();
        dto.setId("1");
        dto.setSerialNumber(001);
        dto.setManufacturer("Seagate");
        dto.setPrice(new BigDecimal("69.99"));
        dto.setStockQuantity(30);
        dto.setVolume(1024);
        return dto;
    }
}
