package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.CarDTO;
import softuni.defense.project.service.CarService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
@CrossOrigin(origins = "http://localhost:3000")
public class CatalogController {

    private final CarService carService;

    public CatalogController(CarService carService) {
        this.carService = carService;
    }

//    @GetMapping("/all")
//    @ResponseBody
//    public ResponseEntity<List<CarDTO>> getAllCars() {
//
//        return ResponseEntity.ok(
//                carService.getAllCars()
//        );
//    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CarDTO>> getSearchedCar(
            @RequestParam(name = "limit") String limit,
            @RequestParam(name = "year") String year,
            @RequestParam(name = "fuel") Optional<String> fuel,
            @RequestParam(name = "make") Optional<String> make,
            @RequestParam(name = "model") Optional<String> model
            ) {

        Map<String, String> parameters = new HashMap<>();

        parameters.put("limit", limit);
        parameters.put("year", year);

        if (fuel.isPresent()) {
            parameters.put("fuel_type", fuel.get());
        }

        if (make.isPresent()) {
            parameters.put("make", make.get());
        }
        if (model.isPresent()) {
            parameters.put("model", model.get());
        }
        return ResponseEntity.ok(carService.getCarsByQueryParameters(parameters));
    }
}
