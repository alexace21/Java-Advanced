package cars.service.RapidApi.service;

import cars.service.RapidApi.model.dtos.CarDTO;

import java.util.List;
import java.util.Map;

public interface ShopService {

    List<CarDTO> fetchAllCars(String limit, String year);

    boolean hasInitializedCarShop();

    List<CarDTO> getCarsByQueryParameters(Map<String, String> parameters);

    CarDTO createCarOffer(CarDTO carDTO);
}
