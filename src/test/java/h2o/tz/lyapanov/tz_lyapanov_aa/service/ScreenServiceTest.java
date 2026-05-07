package h2o.tz.lyapanov.tz_lyapanov_aa.service;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.ScreenRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.ScreenResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.exceptions.DeviceNotFoundException;
import h2o.tz.lyapanov.tz_lyapanov_aa.mapper.ScreenMapper;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.Screen;
import h2o.tz.lyapanov.tz_lyapanov_aa.repository.ScreenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScreenServiceTest {

    @Mock
    private ScreenRepository screenRepository;

    @Mock
    private ScreenMapper screenMapper;

    @InjectMocks
    private ScreenService screenService;

    @Test
    void getDevice_success() {
        String id = "1";

        Screen screen = createScreen();
        ScreenResponseDTO responseDTO = createScreenResponseDTO();

        when(screenRepository.findById(id)).thenReturn(Optional.of(screen));
        when(screenMapper.screenResponseDTOFromScreen(screen)).thenReturn(responseDTO);

        ScreenResponseDTO result = screenService.getDevice(id);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(responseDTO);

        verify(screenRepository, times(1)).findById(id);
        verify(screenMapper, times(1)).screenResponseDTOFromScreen(screen);
    }

    @Test
    void getDevice_nullId_returnNull() {
        ScreenResponseDTO result = screenService.getDevice(null);

        assert(result == null);

        verifyNoInteractions(screenRepository);
        verifyNoInteractions(screenMapper);
    }

    @Test
    void getDevice_emptyId_returnNull() {
        ScreenResponseDTO result = screenService.getDevice("");

        assert (result == null);

        verifyNoInteractions(screenRepository);
        verifyNoInteractions(screenMapper);
    }

    @Test
    void getDevice_notFound_returnNull() {
        String id = "999";

        when(screenRepository.findById(id)).thenReturn(Optional.empty());
        when(screenMapper.screenResponseDTOFromScreen(null)).thenReturn(null);

        ScreenResponseDTO result = screenService.getDevice(id);

        assert (result == null);

        verify(screenRepository, times(1)).findById(id);
        verify(screenMapper, times(1)).screenResponseDTOFromScreen(null);
    }

    @Test
    void getAllDevices_success() {
        Screen screen1 = createScreen();
        Screen screen2 = createSecondScreen();

        ScreenResponseDTO responseDTO1 = createScreenResponseDTO();
        ScreenResponseDTO responseDTO2 = createSecondScreenResponseDTO();

        List<Screen> screens = List.of(screen1, screen2);
        List<ScreenResponseDTO> responseDTOList = List.of(responseDTO1, responseDTO2);

        when(screenRepository.findAll()).thenReturn(screens);
        when(screenMapper.screenResponseDTOListFromScreenList(screens)).thenReturn(responseDTOList);

        List<ScreenResponseDTO> result = screenService.getAllDevices();

        assertThat(result).isNotNull();
        assert (result.size() == 2);
        assertThat(result).isEqualTo(responseDTOList);

        verify(screenRepository, times(1)).findAll();
        verify(screenMapper, times(1)).screenResponseDTOListFromScreenList(screens);
    }

    @Test
    void createDevice_success() {
        ScreenRequestDTO requestDTO = createScreenRequestDTO();
        Screen screen = createScreen();
        Screen savedScreen = createScreen();
        ScreenResponseDTO responseDTO = createScreenResponseDTO();

        when(screenMapper.screenFromScreenRequestDTO(requestDTO)).thenReturn(screen);
        when(screenRepository.save(screen)).thenReturn(savedScreen);
        when(screenMapper.screenResponseDTOFromScreen(savedScreen)).thenReturn(responseDTO);

        ScreenResponseDTO result = screenService.createDevice(requestDTO);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(responseDTO);

        verify(screenMapper, times(1)).screenFromScreenRequestDTO(requestDTO);
        verify(screenRepository, times(1)).save(screen);
        verify(screenMapper, times(1)).screenResponseDTOFromScreen(savedScreen);
    }

    @Test
    void updateDevice_success() {
        String id = "1";

        ScreenRequestDTO requestDTO = createScreenRequestDTO();
        Screen existingScreen = createScreen();
        Screen updatedScreen = createUpdatedScreen();
        ScreenResponseDTO responseDTO = createUpdatedScreenResponseDTO();

        when(screenRepository.findById(id)).thenReturn(Optional.of(existingScreen));
        when(screenRepository.save(existingScreen)).thenReturn(updatedScreen);
        when(screenMapper.screenResponseDTOFromScreen(updatedScreen)).thenReturn(responseDTO);

        ScreenResponseDTO result = screenService.updateDevice(id, requestDTO);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(responseDTO);

        assertThat(existingScreen.getSerialNumber()).isEqualTo(requestDTO.getSerialNumber());
        assertThat(existingScreen.getManufacturer()).isEqualTo(requestDTO.getManufacturer());
        assertThat(existingScreen.getPrice()).isEqualTo(requestDTO.getPrice());
        assertThat(existingScreen.getStockQuantity()).isEqualTo(requestDTO.getStockQuantity());
        assertThat(existingScreen.getDiagonal()).isEqualTo(requestDTO.getDiagonal());

        verify(screenRepository, times(1)).findById(id);
        verify(screenRepository, times(1)).save(existingScreen);
        verify(screenMapper, times(1)).screenResponseDTOFromScreen(updatedScreen);
    }

    @Test
    void updateDevice_notFound_throwException() {
        String id = "999";
        ScreenRequestDTO requestDTO = createScreenRequestDTO();

        when(screenRepository.findById(id)).thenReturn(Optional.empty());

        DeviceNotFoundException exception = assertThrows(
                DeviceNotFoundException.class,
                () -> screenService.updateDevice(id, requestDTO)
        );

        assertThat(exception.getMessage())
                .isEqualTo("Screen with id " + id + " not found");

        verify(screenRepository, times(1)).findById(id);
        verify(screenRepository, never()).save(any());
        verifyNoInteractions(screenMapper);
    }

    private ScreenRequestDTO createScreenRequestDTO() {
        ScreenRequestDTO dto = new ScreenRequestDTO();
        dto.setSerialNumber(001);
        dto.setManufacturer("Samsung");
        dto.setPrice(new BigDecimal("249.99"));
        dto.setStockQuantity(15);
        dto.setDiagonal(27.0f);
        return dto;
    }

    private Screen createScreen() {
        Screen screen = new Screen();
        screen.setSerialNumber(001);
        screen.setManufacturer("Samsung");
        screen.setPrice(new BigDecimal("249.99"));
        screen.setStockQuantity(15);
        screen.setDiagonal(27.0f);
        return screen;
    }

    private Screen createSecondScreen() {
        Screen screen = new Screen();
        screen.setSerialNumber(002);
        screen.setManufacturer("LG");
        screen.setPrice(new BigDecimal("199.90"));
        screen.setStockQuantity(20);
        screen.setDiagonal(23.8f);
        return screen;
    }

    private Screen createUpdatedScreen() {
        Screen screen = new Screen();
        screen.setSerialNumber(001);
        screen.setManufacturer("Samsung");
        screen.setPrice(new BigDecimal("249.99"));
        screen.setStockQuantity(15);
        screen.setDiagonal(27.0f);
        return screen;
    }

    private ScreenResponseDTO createScreenResponseDTO() {
        ScreenResponseDTO dto = new ScreenResponseDTO();
        dto.setId("1");
        dto.setSerialNumber(001);
        dto.setManufacturer("Samsung");
        dto.setPrice(new BigDecimal("249.99"));
        dto.setStockQuantity(15);
        dto.setDiagonal(27.0f);
        return dto;
    }

    private ScreenResponseDTO createSecondScreenResponseDTO() {
        ScreenResponseDTO dto = new ScreenResponseDTO();
        dto.setId("2");
        dto.setSerialNumber(002);
        dto.setManufacturer("LG");
        dto.setPrice(new BigDecimal("199.90"));
        dto.setStockQuantity(20);
        dto.setDiagonal(27.0f);
        return dto;
    }

    private ScreenResponseDTO createUpdatedScreenResponseDTO() {
        ScreenResponseDTO dto = new ScreenResponseDTO();
        dto.setId("1");
        dto.setSerialNumber(001);
        dto.setManufacturer("Samsung");
        dto.setPrice(new BigDecimal("249.99"));
        dto.setStockQuantity(15);
        dto.setDiagonal(27.0f);
        return dto;
    }
}
