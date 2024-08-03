package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.CarDTO;
import softuni.defense.project.service.CarService;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/create")
    public ResponseEntity<CarDTO> createCarOffer(@RequestBody CarDTO carDTO) {
        CarDTO car = this.carService.createCarOffer(carDTO);
        return ResponseEntity.created(URI.create("/cars/" + car.getId())).body(car);
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCarsForSaleOrRent() {
        return ResponseEntity.ok(this.carService.getAllOffers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarDTO> deleteCarOffer(@PathVariable String id) {

        return ResponseEntity.ok(this.carService.deleteCarOfferById(id));
    }
}
