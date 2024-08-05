package softuni.defense.project.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.CarDTO;
import softuni.defense.project.service.CarService;
import softuni.defense.project.utils.Validator;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarService carService;

    private final Validator validator;
    public CarController(CarService carService, Validator validator) {
        this.carService = carService;
        this.validator = validator;
    }

    @PostMapping("/create")
    public ResponseEntity<CarDTO> createCarOffer(@Valid @RequestBody CarDTO carDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            validator.validateCreateOffer(bindingResult);
        }

        CarDTO car = this.carService.createCarOffer(carDTO);
        return ResponseEntity.created(URI.create("/cars/" + car.getId())).body(car);
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCarsForSaleOrRent() {
        return ResponseEntity.ok(this.carService.getAllOffers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarDTO> deleteCarOffer(@PathVariable String id, @RequestBody String userLogin) {
        System.out.println(userLogin);
        return ResponseEntity.ok(this.carService.deleteCarOfferById(id, userLogin));
    }
}
