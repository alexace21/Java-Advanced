package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.defense.project.model.dtos.CarDTO;
import softuni.defense.project.service.CarService;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class CatalogController {

    private final CarService carService;

    public CatalogController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> getAllCars() {

        return ResponseEntity.ok(
                carService.getAllCars()
        );
    }
}
