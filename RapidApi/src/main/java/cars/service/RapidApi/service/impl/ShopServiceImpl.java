package cars.service.RapidApi.service.impl;

import cars.service.RapidApi.config.RapidApiConfig;
import cars.service.RapidApi.model.dtos.CarDTO;
import cars.service.RapidApi.model.entities.CarEntity;
import cars.service.RapidApi.repository.CarRepository;
import cars.service.RapidApi.service.ShopService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {

    private final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);
    private final CarRepository carRepository;

    private final RestClient restClient;
    private final RapidApiConfig rapidApiConfig;
    private final ModelMapper modelMapper;


    public ShopServiceImpl(CarRepository carRepository, RestClient restClient, RapidApiConfig rapidApiConfig, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.restClient = restClient;
        this.rapidApiConfig = rapidApiConfig;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CarDTO> fetchAllCars(String limit, String year) {

        StringBuilder preBuildURI = new StringBuilder();
        preBuildURI.append(rapidApiConfig.getBase());
        preBuildURI.append(rapidApiConfig.getUrl());

        preBuildURI.append("?limit=" + limit);
        preBuildURI.append("&year" + year);

        return restClient
                .get()
                .uri(preBuildURI.toString())
                .accept(MediaType.APPLICATION_JSON)
                .header("x-rapidapi-key", rapidApiConfig.getKey())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public boolean hasInitializedCarShop() {
        return carRepository.count() > 0;
    }

    @Override
    public List<CarDTO> getCarsByQueryParameters(Map<String, String> queryParameters) {

        StringBuilder preBuildURI = new StringBuilder();

        if (queryParameters.size() < 2) {
            return this.fetchAllCars(queryParameters.get("limit"), queryParameters.get("year"));
        }

        preBuildURI.append(rapidApiConfig.getBase());
        preBuildURI.append(rapidApiConfig.getUrl());

        preBuildURI.append("?limit=" + queryParameters.get("limit"));



        for (String key : queryParameters.keySet()) {
            switch(key) {
                case "fuel_type": preBuildURI.append("&fuel_type=" + queryParameters.get(key));
                    break;

                case "year": preBuildURI.append("&year=" + queryParameters.get(key));
                    break;

                case "make": preBuildURI.append("&make=" + queryParameters.get(key));
                    break;

                case "model": preBuildURI.append("&model=" + queryParameters.get(key));
                    break;
            }
        }

        return restClient
                .get()
                .uri(preBuildURI.toString())
                .accept(MediaType.APPLICATION_JSON)
                .header("x-rapidapi-key", rapidApiConfig.getKey())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public CarDTO createCarOffer(CarDTO carDTO) {

//        CarEntity car = new CarEntity(
//                carDTO.city_mpg(),
//                carDTO.combination_mpg(),
//                carDTO.cylinders(),
//                carDTO.displacement(),
//                carDTO.drive(),
//                carDTO.fuel_type(),
//                carDTO.highway_mpg(),
//                carDTO.make(),
//                carDTO.model(),
//                carDTO.transmission(),
//                carDTO.year(),
//                carDTO.owner()
//                );
        CarEntity car = this.modelMapper.map(carDTO, CarEntity.class);
        CarEntity savedEntity = carRepository.save(car);

        return modelMapper.map(savedEntity, CarDTO.class);
    }

//    private static CarDTO map(CarEntity carEntity){
//        // todo - use mapped (e.g. ModelMapper)
//        return new CarDTO(
//                carEntity.getId(),
//                carEntity.getCity_mpg(),
//                carEntity.getCombination_mpg(),
//                carEntity.getCylinders(),
//                carEntity.getDisplacement(),
//                carEntity.getDrive(),
//                carEntity.getFuel_type(),
//                carEntity.getHighway_mpg(),
//                carEntity.getMake(),
//                carEntity.getModel(),
//                carEntity.getTransmission(),
//                carEntity.getYear()
//        );
//    }
}
