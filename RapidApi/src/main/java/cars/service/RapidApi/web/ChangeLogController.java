package cars.service.RapidApi.web;

import cars.service.RapidApi.model.dtos.CarLogDTO;
import cars.service.RapidApi.service.ChangeLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/change-log")
public class ChangeLogController {

    private final ChangeLogService changeLogService;

    public ChangeLogController(ChangeLogService changeLogService) {
        this.changeLogService = changeLogService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarLogDTO>> getAllCars() {
        return ResponseEntity.ok(this.changeLogService.getAllCarLogs());
    }
}
