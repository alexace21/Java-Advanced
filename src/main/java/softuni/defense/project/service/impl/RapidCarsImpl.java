package softuni.defense.project.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import softuni.defense.project.service.RapidCars;

@Service
public class RapidCarsImpl implements RapidCars {

    private RestTemplate restTemplate;

    public String makeApiCall(String method, String url, Object body, HttpHeaders headers) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);

        try {
            // Make the API call
            return restTemplate.exchange(url, HttpMethod.valueOf(method), requestEntity, String.class).getBody();
        } catch (Exception e) {
            // Handle exceptions (e.g., connection errors, timeouts)
            return "Error: " + e.getMessage();
        }
    }
}
