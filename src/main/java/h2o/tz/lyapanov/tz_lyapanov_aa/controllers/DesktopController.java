package h2o.tz.lyapanov.tz_lyapanov_aa.controllers;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.DesktopRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.DesktopResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.service.DesktopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/desktop")
public class DesktopController {

    @Autowired
    private DesktopService desktopService;

    @GetMapping("/getDesktop")
    public ResponseEntity<DesktopResponseDTO> getDesktop(@RequestParam String id) {
        return ResponseEntity.ok().body(desktopService.getDevice(id));
    }

    @GetMapping("/getAllDesktops")
    public ResponseEntity<List<DesktopResponseDTO>> getAllDesktops() {
        return ResponseEntity.ok().body(desktopService.getAllDevices());
    }

    @PostMapping("/createDesktop")
    public ResponseEntity<?> createDesktop(@Valid @RequestBody DesktopRequestDTO desktopRequestDTO) {
        if (desktopRequestDTO == null) {
            return ResponseEntity.badRequest().body("Empty request body - Error");
        } else {
            return ResponseEntity.ok().body(desktopService.createDevice(desktopRequestDTO));
        }
    }

    @PutMapping("/updateDesktop")
    public ResponseEntity<?> updateDesktop(@RequestParam String id, @Valid @RequestBody DesktopRequestDTO desktopRequestDTO) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Param id is empty - Error");
        } else if (desktopRequestDTO == null) {
            return ResponseEntity.badRequest().body("Empty request body - Error");
        } else {
            return ResponseEntity.ok().body(desktopService.updateDevice(id, desktopRequestDTO));
        }
    }

}
