package h2o.tz.lyapanov.tz_lyapanov_aa.service;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.LaptopRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.LaptopResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.exceptions.DeviceNotFoundException;
import h2o.tz.lyapanov.tz_lyapanov_aa.mapper.LaptopMapper;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.Laptop;
import h2o.tz.lyapanov.tz_lyapanov_aa.model.enums.LaptopSize;
import h2o.tz.lyapanov.tz_lyapanov_aa.repository.LaptopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LaptopServiceTest {

    @Mock
    private LaptopRepository laptopRepository;

    @Mock
    private LaptopMapper laptopMapper;

    @InjectMocks
    private LaptopService laptopService;

    @Test
    void getDevice_success() {
        String id = "1";

        Laptop laptop = createLaptop();
        LaptopResponseDTO responseDTO = createLaptopResponseDTO();

        when(laptopRepository.findById(id)).thenReturn(Optional.of(laptop));
        when(laptopMapper.laptopResponseDTOFromLaptop(laptop)).thenReturn(responseDTO);

        LaptopResponseDTO result = laptopService.getDevice(id);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(responseDTO);

        verify(laptopRepository, times(1)).findById(id);
        verify(laptopMapper, times(1)).laptopResponseDTOFromLaptop(laptop);
    }

    @Test
    void getDevice_nullId_returnNull() {
        LaptopResponseDTO result = laptopService.getDevice(null);

        assertNull(result);

        verifyNoInteractions(laptopRepository);
        verifyNoInteractions(laptopMapper);
    }

    @Test
    void getDevice_emptyId_returnNull() {
        LaptopResponseDTO result = laptopService.getDevice("");

        assertNull(result);

        verifyNoInteractions(laptopRepository);
        verifyNoInteractions(laptopMapper);
    }

    @Test
    void getDevice_notFound_returnNull() {
        String id = "999";

        when(laptopRepository.findById(id)).thenReturn(Optional.empty());
        when(laptopMapper.laptopResponseDTOFromLaptop(null)).thenReturn(null);

        LaptopResponseDTO result = laptopService.getDevice(id);

        assertNull(result);

        verify(laptopRepository, times(1)).findById(id);
        verify(laptopMapper, times(1)).laptopResponseDTOFromLaptop(null);
    }

    @Test
    void getAllDevices_success() {
        Laptop laptop1 = createLaptop();
        Laptop laptop2 = createSecondLaptop();

        LaptopResponseDTO responseDTO1 = createLaptopResponseDTO();
        LaptopResponseDTO responseDTO2 = createSecondLaptopResponseDTO();

        List<Laptop> laptops = List.of(laptop1, laptop2);
        List<LaptopResponseDTO> responseDTOList = List.of(responseDTO1, responseDTO2);

        when(laptopRepository.findAll()).thenReturn(laptops);
        when(laptopMapper.laptopResponseDTOListFromLaptopList(laptops)).thenReturn(responseDTOList);

        List<LaptopResponseDTO> result = laptopService.getAllDevices();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(responseDTOList);

        verify(laptopRepository, times(1)).findAll();
        verify(laptopMapper, times(1)).laptopResponseDTOListFromLaptopList(laptops);
    }

    @Test
    void createDevice_success() {
        LaptopRequestDTO requestDTO = createLaptopRequestDTO();
        Laptop laptop = createLaptop();
        Laptop savedLaptop = createLaptop();
        LaptopResponseDTO responseDTO = createLaptopResponseDTO();

        when(laptopMapper.laptopFromLaptopRequestDTO(requestDTO)).thenReturn(laptop);
        when(laptopRepository.save(laptop)).thenReturn(savedLaptop);
        when(laptopMapper.laptopResponseDTOFromLaptop(savedLaptop)).thenReturn(responseDTO);

        LaptopResponseDTO result = laptopService.createDevice(requestDTO);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(responseDTO);

        verify(laptopMapper, times(1)).laptopFromLaptopRequestDTO(requestDTO);
        verify(laptopRepository, times(1)).save(laptop);
        verify(laptopMapper, times(1)).laptopResponseDTOFromLaptop(savedLaptop);
    }

    @Test
    void updateDevice_success() {
        String id = "1";

        LaptopRequestDTO requestDTO = createLaptopRequestDTO();
        Laptop existingLaptop = createLaptop();
        Laptop updatedLaptop = createUpdatedLaptop();
        LaptopResponseDTO responseDTO = createUpdatedLaptopResponseDTO();

        when(laptopRepository.findById(id)).thenReturn(Optional.of(existingLaptop));
        when(laptopRepository.save(existingLaptop)).thenReturn(updatedLaptop);
        when(laptopMapper.laptopResponseDTOFromLaptop(updatedLaptop)).thenReturn(responseDTO);

        LaptopResponseDTO result = laptopService.updateDevice(id, requestDTO);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(responseDTO);

        assertThat(existingLaptop.getSerialNumber()).isEqualTo(requestDTO.getSerialNumber());
        assertThat(existingLaptop.getManufacturer()).isEqualTo(requestDTO.getManufacturer());
        assertThat(existingLaptop.getPrice()).isEqualTo(requestDTO.getPrice());
        assertThat(existingLaptop.getStockQuantity()).isEqualTo(requestDTO.getStockQuantity());
        assertThat(existingLaptop.getSize()).isEqualTo(requestDTO.getSize());

        verify(laptopRepository, times(1)).findById(id);
        verify(laptopRepository, times(1)).save(existingLaptop);
        verify(laptopMapper, times(1)).laptopResponseDTOFromLaptop(updatedLaptop);
    }

    @Test
    void updateDevice_notFound_throwException() {
        String id = "999";
        LaptopRequestDTO requestDTO = createLaptopRequestDTO();

        when(laptopRepository.findById(id)).thenReturn(Optional.empty());

        DeviceNotFoundException exception = assertThrows(
                DeviceNotFoundException.class,
                () -> laptopService.updateDevice(id, requestDTO)
        );

        assertThat(exception.getMessage())
                .isEqualTo("Laptop with id " + id + " not found");

        verify(laptopRepository, times(1)).findById(id);
        verify(laptopRepository, never()).save(any());
        verifyNoInteractions(laptopMapper);
    }

    private LaptopRequestDTO createLaptopRequestDTO() {
        LaptopRequestDTO dto = new LaptopRequestDTO();
        dto.setSerialNumber(001);
        dto.setManufacturer("ASUS");
        dto.setPrice(new BigDecimal("1099.90"));
        dto.setStockQuantity(9);
        dto.setSize(LaptopSize.INCH_15);
        return dto;
    }

    private Laptop createLaptop() {
        Laptop laptop = new Laptop();
        laptop.setSerialNumber(001);
        laptop.setManufacturer("ASUS");
        laptop.setPrice(new BigDecimal("1099.90"));
        laptop.setStockQuantity(9);
        laptop.setSize(LaptopSize.INCH_15);
        return laptop;
    }

    private Laptop createSecondLaptop() {
        Laptop laptop = new Laptop();
        laptop.setSerialNumber(002);
        laptop.setManufacturer("Apple");
        laptop.setPrice(new BigDecimal("1499.99"));
        laptop.setStockQuantity(5);
        laptop.setSize(LaptopSize.INCH_13);
        return laptop;
    }

    private Laptop createUpdatedLaptop() {
        Laptop laptop = new Laptop();
        laptop.setSerialNumber(001);
        laptop.setManufacturer("ASUS");
        laptop.setPrice(new BigDecimal("1099.90"));
        laptop.setStockQuantity(9);
        laptop.setSize(LaptopSize.INCH_15);
        return laptop;
    }

    private LaptopResponseDTO createLaptopResponseDTO() {
        LaptopResponseDTO dto = new LaptopResponseDTO();
        dto.setId("1");
        dto.setSerialNumber(001);
        dto.setManufacturer("ASUS");
        dto.setPrice(new BigDecimal("1099.90"));
        dto.setStockQuantity(9);
        dto.setSize(LaptopSize.INCH_15);
        return dto;
    }

    private LaptopResponseDTO createSecondLaptopResponseDTO() {
        LaptopResponseDTO dto = new LaptopResponseDTO();
        dto.setId("2");
        dto.setSerialNumber(002);
        dto.setManufacturer("Apple");
        dto.setPrice(new BigDecimal("1499.99"));
        dto.setStockQuantity(5);
        dto.setSize(LaptopSize.INCH_13);
        return dto;
    }

    private LaptopResponseDTO createUpdatedLaptopResponseDTO() {
        LaptopResponseDTO dto = new LaptopResponseDTO();
        dto.setId("1");
        dto.setSerialNumber(001);
        dto.setManufacturer("ASUS");
        dto.setPrice(new BigDecimal("1099.90"));
        dto.setStockQuantity(9);
        dto.setSize(LaptopSize.INCH_15);
        return dto;
    }

}
