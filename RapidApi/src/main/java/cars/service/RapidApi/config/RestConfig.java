package cars.service.RapidApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean("offersRestClient")
    public RestClient offersRestClient(RapidApiConfig rapidApiConfig) {
        return RestClient.builder()
                .baseUrl(rapidApiConfig.getBase())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
