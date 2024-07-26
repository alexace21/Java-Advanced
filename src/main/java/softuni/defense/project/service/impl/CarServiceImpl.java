package softuni.defense.project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import softuni.defense.project.model.dtos.CarDTO;
import softuni.defense.project.service.CarService;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);
    private final RestClient carRestClient;

    public CarServiceImpl(@Qualifier("carsRestClient") RestClient carRestClient) {
        this.carRestClient = carRestClient;
    }

//    public String makeApiCall(String method, String url, Object body, HttpHeaders headers) {
//        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);
//
//        try {
//            // Make the API call
//            return restTemplate.exchange(url, HttpMethod.valueOf(method), requestEntity, String.class).getBody();
//        } catch (Exception e) {
//            // Handle exceptions (e.g., connection errors, timeouts)
//            return "Error: " + e.getMessage();
//        }
//    }

    @Override
    public List<CarDTO> getAllCars() {
        LOGGER.info("Get All Cars...");

        return carRestClient
                .get()
                .uri("/shop")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

    }
}
