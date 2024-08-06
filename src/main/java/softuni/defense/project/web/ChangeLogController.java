package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.CarLogDTO;
import softuni.defense.project.model.dtos.FeedbackLogDTO;
import softuni.defense.project.model.dtos.UserLogDTO;
import softuni.defense.project.service.ChangeLogService;

import java.util.List;

@Controller
@RequestMapping("/change-log")
@CrossOrigin(origins = "http://localhost:3000")
public class ChangeLogController {

    private final ChangeLogService changeLogService;

    public ChangeLogController(ChangeLogService changeLogService) {
        this.changeLogService = changeLogService;
    }

    @GetMapping("/feedback")
    @PreAuthorize("hasAuthority('ADMIN')")

    public ResponseEntity<List<FeedbackLogDTO>> getAllFeedbackLogs() {
        return ResponseEntity.ok(this.changeLogService.getAllFeedbackLogs());
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")

    public ResponseEntity<List<UserLogDTO>> getAllRegisteredUsers() {
            return ResponseEntity.ok(this.changeLogService.getAllRegisteredUsers());
    }

    @GetMapping("/cars")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CarLogDTO>> getAllRegisteredCars() {
        return ResponseEntity.ok(this.changeLogService.getAllCarLogs());
    }
}
