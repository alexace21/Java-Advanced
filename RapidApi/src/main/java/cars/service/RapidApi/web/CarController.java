package cars.service.RapidApi.web;

import cars.service.RapidApi.model.dtos.CarDTO;
import cars.service.RapidApi.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    private final ShopService shopService;

    public CarController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        System.out.println("Fetching cars...");
        return ResponseEntity.ok(
                shopService.fetchAllCars()
        );
    }

//    @GetMapping
//    public ResponseEntity<List<CarDTO>> fetchCarByParameters(@RequestParam(name = "make") String make, @RequestParam(name = "model") String model) {
//        return ResponseEntity.ok(
//                shopService.fetchAllCars()
//        );
//    }
}
