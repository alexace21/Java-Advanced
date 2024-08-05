package softuni.defense.project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import softuni.defense.project.config.UserAuthProvider;
import softuni.defense.project.config.exception.AppException;
import softuni.defense.project.model.dtos.CarDTO;
import softuni.defense.project.service.CarService;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    private Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);
    private final RestClient carRestClient;
    private final UserAuthProvider authProvider;

    public CarServiceImpl(@Qualifier("carsRestClient") RestClient carRestClient, UserAuthProvider authProvider) {
        this.carRestClient = carRestClient;
        this.authProvider = authProvider;
    }

    @Override
    public List<CarDTO> getAllCars(String limit, String year) {
        LOGGER.info("FETCH All Cars...");

        StringBuilder preBuildURI = new StringBuilder();
        preBuildURI.append("/shop?");
        preBuildURI.append("limit=" + limit);
        preBuildURI.append("&year=" + year);

        return carRestClient
                .get()
                .uri(preBuildURI.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

    }

    @Override
    public List<CarDTO> getCarsByQueryParameters(Map<String, String> queryParameters) {
        LOGGER.info("FETCH all by query parameters cars");

        StringBuilder preBuildURI = new StringBuilder();
        if (queryParameters.size() == 2) {
            return this.getAllCars(queryParameters.get("limit"), queryParameters.get("year"));
        }

        preBuildURI.append("/shop?");
        preBuildURI.append("limit=" + queryParameters.get("limit"));
        preBuildURI.append("&year=" + queryParameters.get("year"));


        for (String key : queryParameters.keySet()) {
            switch(key) {
                case "fuel_type": preBuildURI.append("&fuel_type=" + queryParameters.get(key));
                break;

                case "make": preBuildURI.append("&make=" + queryParameters.get(key));
                break;

                case "model": preBuildURI.append("&model=" + queryParameters.get(key));
                break;
            }
        }

        return carRestClient
                .get()
                .uri(preBuildURI.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public CarDTO createCarOffer(CarDTO carDTO) {
        LOGGER.info("Creating offer..." + carDTO);

        StringBuilder preBuildURI = new StringBuilder();
        preBuildURI.append("/shop/create");

        boolean authorize = authProvider.getCurrentLogin().equals(carDTO.getOwner());

        if (authorize) {
            carDTO.setOwner(authProvider.getCurrentLogin());

            return carRestClient
                    .post()
                    .uri(preBuildURI.toString())
                    .body(carDTO)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
        }

        throw new AppException("Unauthorized access", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public List<CarDTO> getAllOffers() {
        LOGGER.info("FETCH All Cars from repository...");

        StringBuilder preBuildURI = new StringBuilder();
        preBuildURI.append("/shop/all");

        return carRestClient
                .get()
                .uri(preBuildURI.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public CarDTO deleteCarOfferById(String id, String login) {
        LOGGER.info("Deleting offer with ID... " + id);

        boolean isOwner =  authProvider.getCurrentLogin().equals(login);

        StringBuilder preBuildURI = new StringBuilder();

        preBuildURI.append("/shop/");
        preBuildURI.append(id);

        if (isOwner) {
            return carRestClient
                    .delete()
                    .uri(preBuildURI.toString())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
        } else {
            throw new RuntimeException("Unauthorized");
        }


    }

}
