package cars.service.RapidApi.service.impl;

import cars.service.RapidApi.config.RapidApiConfig;
import cars.service.RapidApi.model.dtos.CarDTO;
import cars.service.RapidApi.model.entities.CarEntity;
import cars.service.RapidApi.repository.CarRepository;
import cars.service.RapidApi.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);
    private final CarRepository carRepository;

    private final RestClient restClient;
    private final RapidApiConfig rapidApiConfig;


    public ShopServiceImpl(CarRepository carRepository, RestClient restClient, RapidApiConfig rapidApiConfig) {
        this.carRepository = carRepository;
        this.restClient = restClient;
        this.rapidApiConfig = rapidApiConfig;
    }


    @Override
    public CarDTO fetchAllCars() {
//        return carRepository.findAll()
//                .stream()
//                .map(ShopServiceImpl::map)
//                .collect(Collectors.toList());

        return restClient
                .get()
                .uri(rapidApiConfig.getBase(), rapidApiConfig.getUrl(), rapidApiConfig.getKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(CarDTO.class);
    }

    @Override
    public boolean hasInitializedExRates() {
        return carRepository.count() > 0;
    }

    private static CarDTO map(CarEntity carEntity){
        // todo - use mapped (e.g. ModelMapper)
        return new CarDTO(
                carEntity.getId(),
                carEntity.getCity_mpg(),
                carEntity.getCombination_mpg(),
                carEntity.getCylinders(),
                carEntity.getDisplacement(),
                carEntity.getDrive(),
                carEntity.getFuel_type(),
                carEntity.getHighway_mpg(),
                carEntity.getMake(),
                carEntity.getModel(),
                carEntity.getTransmission(),
                carEntity.getYear()
        );
    }
}
