package h2o.tz.lyapanov.tz_lyapanov_aa.controllers;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.HardDriveRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.HardDriveResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.service.HardDriveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "HardDrive",
        description = "Methods for managing hard drives."
)
@RestController
@RequestMapping("/api/v1/hardDrive/")
public class HardDriveController {

    @Autowired
    private HardDriveService hardDriveService;

    @Operation(
            summary = "Get hard drive by Id",
            description = "Return a hard drive by its unique identifier"
    )
    @GetMapping("/getHardDrive")
    public ResponseEntity<HardDriveResponseDTO> getHardDrive(@RequestParam String id) {
        return ResponseEntity.ok().body(hardDriveService.getDevice(id));
    }

    @Operation(
            summary = "Get all hard drives",
            description = "Returns a list of all hard drives stored in the inventory"
    )
    @GetMapping("/getAllHardDrives")
    public ResponseEntity<List<HardDriveResponseDTO>> getAllHardDrives() {
        return ResponseEntity.ok().body(hardDriveService.getAllDevices());
    }

    @Operation(
            summary = "Create a new hard drive",
            description = "Creates a new hard drive and saves it to the inventory"
    )
    @PostMapping("/createHardDrive")
    public ResponseEntity<?> createHardDrive(@Valid @RequestBody HardDriveRequestDTO hardDriveRequestDTO) {
        if (hardDriveRequestDTO == null) {
            return ResponseEntity.badRequest().body("Empty request body - Error");
        } else {
            return ResponseEntity.ok().body(hardDriveService.createDevice(hardDriveRequestDTO));
        }
    }

    @Operation(
            summary = "Update an existing hard drive",
            description = "Updates all fields of an existing hard drive by ID"
    )
    @PutMapping("/updateHardDrive")
    public ResponseEntity<?> updateHardDrive(@RequestParam String id, @Valid @RequestBody HardDriveRequestDTO hardDriveRequestDTO) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Param id is empty - Error");
        } else if (hardDriveService == null) {
            return ResponseEntity.badRequest().body("Empty request body - Error");
        } else {
            return ResponseEntity.ok().body(hardDriveService.updateDevice(id, hardDriveRequestDTO));
        }
    }



}
