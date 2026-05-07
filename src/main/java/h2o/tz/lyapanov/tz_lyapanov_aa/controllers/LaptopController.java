package h2o.tz.lyapanov.tz_lyapanov_aa.controllers;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.LaptopRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.LaptopResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.service.LaptopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Laptop",
        description = "Methods for managing laptop."
)
@RestController
@RequestMapping("/api/v1/laptop/")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;

    @Operation(
            summary = "Get laptop by Id",
            description = "Return a laptop by its unique identifier"
    )
    @GetMapping("/getLaptop")
    public ResponseEntity<LaptopResponseDTO> getLaptop(@RequestParam String id) {
        return ResponseEntity.ok().body(laptopService.getDevice(id));
    }

    @Operation(
            summary = "Get all laptops",
            description = "Returns a list of all laptops stored in the inventory"
    )
    @GetMapping("/getAllLaptops")
    public ResponseEntity<List<LaptopResponseDTO>> getAllLaptops() {
        return ResponseEntity.ok().body(laptopService.getAllDevices());
    }

    @Operation(
            summary = "Create a new laptop",
            description = "Creates a new laptop and saves it to the inventory"
    )
    @PostMapping("/createLaptop")
    public ResponseEntity<?> createLaptop(@Valid @RequestBody LaptopRequestDTO laptopRequestDTO) {
        if (laptopRequestDTO == null) {
            return ResponseEntity.badRequest().body("Empty request body - Error");
        } else {
            return ResponseEntity.ok().body(laptopService.createDevice(laptopRequestDTO));
        }
    }

    @Operation(
            summary = "Update an existing laptop",
            description = "Updates all fields of an existing laptop by ID"
    )
    @PutMapping("/updateLaptop")
    public ResponseEntity<?> updateLaptop(@RequestParam String id, @Valid @RequestBody LaptopRequestDTO laptopRequestDTO) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Param id is empty - Error");
        } else if (laptopRequestDTO == null) {
            return ResponseEntity.badRequest().body("Empty request body - Error");
        } else {
            return ResponseEntity.ok().body(laptopService.updateDevice(id, laptopRequestDTO));
        }
    }
}
