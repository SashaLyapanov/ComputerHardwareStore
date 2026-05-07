package h2o.tz.lyapanov.tz_lyapanov_aa.controllers;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.DesktopRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.DesktopResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.service.DesktopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Desktop computers",
        description = "Methods for managing desktop computers."
)
@RestController
@RequestMapping("/api/v1/desktop")
public class DesktopController {

    @Autowired
    private DesktopService desktopService;

    @Operation(
            summary = "Get desktop computer by Id",
            description = "Return a desktop computer by its unique identifier"
    )
    @GetMapping("/getDesktop")
    public ResponseEntity<DesktopResponseDTO> getDesktop(@RequestParam String id) {
        return ResponseEntity.ok().body(desktopService.getDevice(id));
    }

    @Operation(
            summary = "Get all desktop computers",
            description = "Returns a list of all desktop computers stored in the inventory"
    )
    @GetMapping("/getAllDesktops")
    public ResponseEntity<List<DesktopResponseDTO>> getAllDesktops() {
        return ResponseEntity.ok().body(desktopService.getAllDevices());
    }

    @Operation(
            summary = "Create a new desktop computer",
            description = "Creates a new desktop computer and saves it to the inventory"
    )
    @PostMapping("/createDesktop")
    public ResponseEntity<?> createDesktop(@Valid @RequestBody DesktopRequestDTO desktopRequestDTO) {
        if (desktopRequestDTO == null) {
            return ResponseEntity.badRequest().body("Empty request body - Error");
        } else {
            return ResponseEntity.ok().body(desktopService.createDevice(desktopRequestDTO));
        }
    }

    @Operation(
            summary = "Update an existing desktop computer",
            description = "Updates all fields of an existing desktop computer by ID"
    )
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
