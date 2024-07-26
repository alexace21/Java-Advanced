package cars.service.RapidApi.service;

import cars.service.RapidApi.model.dtos.CarDTO;

import java.util.List;

public interface ShopService {

    List<CarDTO> fetchAllCars();

    boolean hasInitializedCarShop();

}
