package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.CarDTO;
import softuni.defense.project.service.CarService;

import java.util.List;

@Controller
@RequestMapping("/shop")
@CrossOrigin(origins = "http://localhost:3000")
public class CatalogController {

    private final CarService carService;

    public CatalogController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<CarDTO>> getAllCars() {

        return ResponseEntity.ok(
                carService.getAllCars()
        );
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CarDTO>> getSearchedCar(
            @RequestParam(name = "make") String make,
            @RequestParam(name = "model") String model,
            @RequestParam(name = "year") String year,
            @RequestParam(name = "fuel") String fuel
            ) {
        System.out.println(make);
        System.out.println(model);
        System.out.println(year);
        System.out.println(fuel);
        return ResponseEntity.ok(
                carService.getCarByMakeAndModel(make, model)
        );
    }
}
