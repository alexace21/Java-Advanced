package softuni.defense.project.service;

import softuni.defense.project.model.dtos.CarDTO;

import java.util.List;
import java.util.Map;

public interface CarService {

    List<CarDTO> getAllCars(String limit, String year);

    List<CarDTO> getCarsByQueryParameters(Map<String, String> queryParameters);

    CarDTO createCarOffer(CarDTO carDTO);

    List<CarDTO> getAllOffers();

    CarDTO deleteCarOfferById(String id);
}
