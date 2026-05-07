package h2o.tz.lyapanov.tz_lyapanov_aa.controllers;

import h2o.tz.lyapanov.tz_lyapanov_aa.dto.request.ScreenRequestDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.dto.response.ScreenResponseDTO;
import h2o.tz.lyapanov.tz_lyapanov_aa.service.ScreenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Screen",
        description = "Methods for managing screen."
)
@RestController
@RequestMapping("/api/v1/screen/")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @Operation(
            summary = "Get screen by Id",
            description = "Return a screen by its unique identifier"
    )
    @GetMapping("/getScreen")
    public ResponseEntity<ScreenResponseDTO> getScreen(@RequestParam String id) {
        return ResponseEntity.ok().body(screenService.getDevice(id));
    }

    @Operation(
            summary = "Get all screens",
            description = "Returns a list of all screens stored in the inventory"
    )
    @GetMapping("/getAllScreen")
    public ResponseEntity<List<ScreenResponseDTO>> getAllScreen() {
        return ResponseEntity.ok().body(screenService.getAllDevices());
    }

    @Operation(
            summary = "Create a new screen",
            description = "Creates a new screen and saves it to the inventory"
    )
    @PostMapping("/createScreen")
    public ResponseEntity<?> createScreen(@Valid @RequestBody ScreenRequestDTO screenRequestDTO) {
        if (screenRequestDTO == null) {
            return ResponseEntity.badRequest().body("Empty request body - Error");
        } else {
            return ResponseEntity.ok().body(screenService.createDevice(screenRequestDTO));
        }
    }

    @Operation(
            summary = "Update an existing screen",
            description = "Updates all fields of an existing screen by ID"
    )
    @PutMapping("/updateScreen")
    public ResponseEntity<?> updateScreen(@RequestParam String id, @Valid @RequestBody ScreenRequestDTO screenRequestDTO) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Param id is empty - Error");
        } else if (screenRequestDTO == null) {
            return ResponseEntity.badRequest().body("Empty request body - Error");
        } else {
            return ResponseEntity.ok().body(screenService.updateDevice(id, screenRequestDTO));
        }
    }
}
