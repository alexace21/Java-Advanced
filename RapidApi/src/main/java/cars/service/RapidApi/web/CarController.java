package cars.service.RapidApi.web;

import cars.service.RapidApi.model.dtos.CarDTO;
import cars.service.RapidApi.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    private final ShopService shopService;

    public CarController(ShopService shopService) {
        this.shopService = shopService;
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CarDTO>> getSearchedCar(
            @RequestParam(name = "limit") String limit,
            @RequestParam(name = "fuel_type") Optional<String> fuel_type,
            @RequestParam(name = "year") Optional<String> year,
            @RequestParam(name = "make") Optional<String> make,
            @RequestParam(name = "model") Optional<String> model
    ) {

        Map<String, String> parameters = new HashMap<>();

        parameters.put("limit", limit);

        if (fuel_type.isPresent()) {
            parameters.put("fuel_type", fuel_type.get());
        }

        if (year.isPresent()){
            parameters.put("year", year.get());
        }

        if (make.isPresent()) {
            parameters.put("make", make.get());
        }
        if (model.isPresent()) {
            parameters.put("model", model.get());
        }

        return ResponseEntity.ok(shopService.getCarsByQueryParameters(parameters));
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<CarDTO> createCarOffer(@RequestBody CarDTO carDTO) {
        CarDTO car = this.shopService.createCarOffer(carDTO);

        return ResponseEntity.ok(car);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return ResponseEntity.ok(this.shopService.fetchAllOffers());
    }
}
