package h2o.tz.lyapanov.tz_lyapanov_aa.controllers;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.HardDriveRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.HardDriveResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.service.HardDriveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hardDrive/")
public class HardDriveController {

    @Autowired
    private HardDriveService hardDriveService;

    @GetMapping("/getHardDrive")
    public ResponseEntity<HardDriveResponseDTO> getHardDrive(@RequestParam String id) {
        return ResponseEntity.ok().body(hardDriveService.getDevice(id));
    }

    @GetMapping("/getAllHardDrives")
    public ResponseEntity<List<HardDriveResponseDTO>> getAllHardDrives() {
        return ResponseEntity.ok().body(hardDriveService.getAllDevices());
    }

    @PostMapping("/createHardDrive")
    public ResponseEntity<?> createHardDrive(@Valid @RequestBody HardDriveRequestDTO hardDriveRequestDTO) {
        if (hardDriveRequestDTO == null) {
            return ResponseEntity.badRequest().body("Empty request body - Error");
        } else {
            return ResponseEntity.ok().body(hardDriveService.createDevice(hardDriveRequestDTO));
        }
    }

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
