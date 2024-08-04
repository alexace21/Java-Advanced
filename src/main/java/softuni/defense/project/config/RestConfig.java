package softuni.defense.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Primary
    @Bean("carsRestClient")
    public RestClient carsRestClient(CarShopConfig carShopConfig) {
        return RestClient.builder()
                .baseUrl(carShopConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    @Bean("coinGeckoRestClient")
    public RestClient coinGeckoRestClient(CoinGeckoConfig coinGeckoConfig) {
        return RestClient.builder()
                .baseUrl(coinGeckoConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
