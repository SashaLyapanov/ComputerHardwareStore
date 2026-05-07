package h2o.tz.lyapanov.tz_lyapanov_aa.service;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.DesktopRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.DesktopResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.exceptions.DeviceNotFoundException;
import h2o.tz.lyapanov.tz_lyapanov_aa.mapper.DesktopMapper;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.Desktop;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.enums.DesktopType;
import h2o.tz.lyapanov.tz_lyapanov_aa.repository.DesktopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DesktopServiceTest {

    @Mock
    private DesktopRepository desktopRepository;
    @Mock
    private DesktopMapper desktopMapper;
    @InjectMocks
    private DesktopService desktopService;

    @Test
    void getDevice_success() {
        String id = "1";

        Desktop desktop = createDesktop();
        DesktopResponseDTO desktopResponseDTO = createDesktopResponseDTO();

        when(desktopRepository.findById(id)).thenReturn(Optional.of(desktop));
        when(desktopMapper.desktopResponseDTOFromDesktop(desktop)).thenReturn(desktopResponseDTO);

        DesktopResponseDTO result = desktopService.getDevice(id);

        org.assertj.core.api.Assertions.assertThat(result).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(desktopResponseDTO);

        verify(desktopRepository, times(1)).findById(id);
        verify(desktopMapper, times(1)).desktopResponseDTOFromDesktop(desktop);
    }

    @Test
    void getDevice_notFound_NullId() {
        String id = null;
        DesktopResponseDTO desktopResponseDTO = desktopService.getDevice(id);

        assert (desktopResponseDTO == null);

        verifyNoInteractions(desktopRepository);
        verifyNoInteractions(desktopMapper);
    }

    @Test
    void getDevice_notFound_EmptyId() {
        String id = "";
        DesktopResponseDTO desktopResponseDTO = desktopService.getDevice(id);

        assert (desktopResponseDTO == null);

        verifyNoInteractions(desktopRepository);
        verifyNoInteractions(desktopMapper);
    }

    @Test
    void getDevice_notFound_IncorrectId() {
        String id = "234";

        when(desktopRepository.findById(id)).thenReturn(Optional.empty());
        when(desktopMapper.desktopResponseDTOFromDesktop(null)).thenReturn(null);

        DesktopResponseDTO result = desktopService.getDevice(id);
        assert (result == null);

        verify(desktopRepository, times(1)).findById(id);
        verify(desktopMapper, times(1)).desktopResponseDTOFromDesktop(null);
    }

    @Test
    void getAllDevices_success() {
        Desktop desktop1 = createDesktop();
        Desktop desktop2 = createSecondDesktop();

        DesktopResponseDTO desktopResponseDTO1 = createDesktopResponseDTO();
        DesktopResponseDTO desktopResponseDTO2 = createSecondDesktopResponseDTO();

        List<Desktop> desktops = List.of(desktop1, desktop2);
        List<DesktopResponseDTO> responseDTOs = List.of(desktopResponseDTO1, desktopResponseDTO2);

        when(desktopRepository.findAll()).thenReturn(desktops);
        when(desktopMapper.desktopResponseDTOListFromDesktopList(desktops)).thenReturn(responseDTOs);

        List<DesktopResponseDTO> result = desktopService.getAllDevices();

        assert (result != null);
        assert (result.size() == 2);
        assert (result.equals(responseDTOs));

        verify(desktopRepository, times(1)).findAll();
        verify(desktopMapper, times(1)).desktopResponseDTOListFromDesktopList(desktops);
    }

    @Test
    void createDevice_success() {
        DesktopRequestDTO desktopRequestDTO = createDesktopRequestDTO();
        Desktop desktop = createDesktop();
        Desktop savedDesktop = createDesktop();
        DesktopResponseDTO desktopResponseDTO = createDesktopResponseDTO();

        when(desktopMapper.desktopFromDesktopRequestDTO(desktopRequestDTO)).thenReturn(desktop);
        when(desktopRepository.save(desktop)).thenReturn(savedDesktop);
        when(desktopMapper.desktopResponseDTOFromDesktop(desktop)).thenReturn(desktopResponseDTO);

        DesktopResponseDTO result = desktopService.createDevice(desktopRequestDTO);

        assert (result != null);
        assert (result.equals(desktopResponseDTO));

        verify(desktopMapper, times(1)).desktopFromDesktopRequestDTO(desktopRequestDTO);
        verify(desktopRepository, times(1)).save(desktop);
        verify(desktopMapper, times(1)).desktopResponseDTOFromDesktop(desktop);
    }

    @Test
    void updateDevice_success() {
        String id = "1";

        DesktopRequestDTO desktopRequestDTO = createDesktopRequestDTO();
        Desktop existingDesktop = createDesktop();
        Desktop updatedDesktop = createDesktop();
        DesktopResponseDTO desktopResponseDTO = createDesktopResponseDTO();

        when(desktopRepository.findById(id)).thenReturn(Optional.of(existingDesktop));
        when(desktopRepository.save(existingDesktop)).thenReturn(updatedDesktop);
        when(desktopMapper.desktopResponseDTOFromDesktop(updatedDesktop)).thenReturn(desktopResponseDTO);

        DesktopResponseDTO result = desktopService.updateDevice(id, desktopRequestDTO);

        assert (result != null);
        assert (result.equals(desktopResponseDTO));

        org.assertj.core.api.Assertions.assertThat(desktopRequestDTO.getSerialNumber()).isEqualTo(existingDesktop.getSerialNumber());
        org.assertj.core.api.Assertions.assertThat(desktopRequestDTO.getManufacturer()).isEqualTo(existingDesktop.getManufacturer());
        org.assertj.core.api.Assertions.assertThat(desktopRequestDTO.getFormFactor()).isEqualTo(existingDesktop.getFormFactor());
        org.assertj.core.api.Assertions.assertThat(desktopRequestDTO.getPrice()).isEqualTo(existingDesktop.getPrice());
        org.assertj.core.api.Assertions.assertThat(desktopRequestDTO.getStockQuantity()).isEqualTo(existingDesktop.getStockQuantity());

        verify(desktopRepository, times(1)).findById(id);
        verify(desktopRepository, times(1)).save(existingDesktop);
        verify(desktopMapper, times(1)).desktopResponseDTOFromDesktop(updatedDesktop);
    }

    @Test
    void updateDevice_notFound_IncorrectId() {
        String id = "234";
        DesktopRequestDTO desktopRequestDTO = createDesktopRequestDTO();

        when(desktopRepository.findById(id)).thenReturn(Optional.empty());

        DeviceNotFoundException exception = assertThrows(
                DeviceNotFoundException.class,
                () -> desktopService.updateDevice(id, desktopRequestDTO)
        );

        org.assertj.core.api.Assertions.assertThat(exception.getMessage())
                .isEqualTo("Desktop with id " + id + " not found.");

        verify(desktopRepository, times(1)).findById(id);
        verify(desktopRepository, never()).save(any());
        verifyNoInteractions(desktopMapper);
    }

    private DesktopRequestDTO createDesktopRequestDTO() {
        DesktopRequestDTO dto = new DesktopRequestDTO();
        dto.setSerialNumber(001);
        dto.setManufacturer("HP");
        dto.setPrice(new BigDecimal("799.99"));
        dto.setStockQuantity(10);
        dto.setFormFactor(DesktopType.DESKTOP_TYPE);
        return dto;
    }

    private Desktop createDesktop() {
        Desktop desktop = new Desktop();
        desktop.setSerialNumber(001);
        desktop.setManufacturer("HP");
        desktop.setPrice(new BigDecimal("799.99"));
        desktop.setStockQuantity(10);
        desktop.setFormFactor(DesktopType.DESKTOP_TYPE);
        return desktop;
    }

    private Desktop createSecondDesktop() {
        Desktop desktop = new Desktop();
        desktop.setSerialNumber(002);
        desktop.setManufacturer("Lenovo");
        desktop.setPrice(new BigDecimal("549.50"));
        desktop.setStockQuantity(7);
        desktop.setFormFactor(DesktopType.MONOBLOCK_TYPE);
        return desktop;
    }

    private Desktop createUpdatedDesktop() {
        Desktop desktop = new Desktop();
        desktop.setSerialNumber(001);
        desktop.setManufacturer("HP");
        desktop.setPrice(new BigDecimal("799.99"));
        desktop.setStockQuantity(10);
        desktop.setFormFactor(DesktopType.DESKTOP_TYPE);
        return desktop;
    }

    private DesktopResponseDTO createDesktopResponseDTO() {
        DesktopResponseDTO dto = new DesktopResponseDTO();
        dto.setId("1");
        dto.setSerialNumber(001);
        dto.setManufacturer("HP");
        dto.setPrice(new BigDecimal("799.99"));
        dto.setStockQuantity(10);
        dto.setFormFactor(DesktopType.DESKTOP_TYPE);
        return dto;
    }

    private DesktopResponseDTO createSecondDesktopResponseDTO() {
        DesktopResponseDTO dto = new DesktopResponseDTO();
        dto.setId("2");
        dto.setSerialNumber(002);
        dto.setManufacturer("Lenovo");
        dto.setPrice(new BigDecimal("549.50"));
        dto.setStockQuantity(7);
        dto.setFormFactor(DesktopType.MONOBLOCK_TYPE);
        return dto;
    }

    private DesktopResponseDTO createUpdatedDesktopResponseDTO() {
        DesktopResponseDTO dto = new DesktopResponseDTO();
        dto.setId("1");
        dto.setSerialNumber(001);
        dto.setManufacturer("HP");
        dto.setPrice(new BigDecimal("799.99"));
        dto.setStockQuantity(10);
        dto.setFormFactor(DesktopType.DESKTOP_TYPE);
        return dto;
    }

}
