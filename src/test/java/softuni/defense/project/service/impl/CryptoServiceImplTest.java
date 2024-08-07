package softuni.defense.project.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import softuni.defense.project.config.CoinGeckoConfig;
import softuni.defense.project.model.dtos.BitcoinDTO;
import softuni.defense.project.model.dtos.RippleCoinDTO;
import softuni.defense.project.model.entities.CoinPriceEntity;
import softuni.defense.project.repositories.CoinPriceRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

//@Disabled
@ExtendWith(MockitoExtension.class)
public class CryptoServiceImplTest {
    @Mock
    private CoinPriceRepository coinPriceRepository;

//    @Mock
//    private RestClient cryptoRestClient;
//
//    @Mock
//    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;
//
//    @Mock
//    private RestClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private CoinGeckoConfig cryptoConfig;  // Mock the configuration class

    @InjectMocks
    private CryptoServiceImpl coinService;

//    @BeforeEach
//    void setUp() {
//        when(cryptoRestClient.get()).thenReturn(requestHeadersUriSpec);
//        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
//        when(requestHeadersSpec.accept(any())).thenReturn(requestHeadersSpec);
//        when(cryptoConfig.getBaseUrl()).thenReturn("https://api.coingecko.com/api/v3");
//    }

    @Test
    void getRippleAndBitcoinData_ShouldReturnCoinPrices() {
        List<CoinPriceEntity> mockPrices = List.of(
                new CoinPriceEntity(40000, "bitcoin"),
                new CoinPriceEntity(1, "ripple")
        );
        when(coinPriceRepository.findAll()).thenReturn(mockPrices);

        List<CoinPriceEntity> result = coinService.getRippleAndBitcoinData();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("bitcoin", result.get(0).getName());
        assertEquals("ripple", result.get(1).getName());
    }

//    @Test
//    void updateRippleData_ShouldUpdateExistingCoinPrice() {
//        CoinPriceEntity ripple = new CoinPriceEntity(1, "ripple");
//        RippleCoinDTO rippleDTO = new RippleCoinDTO(new CoinPriceEntity(Double.valueOf(2), "ripple"));
//        when(coinPriceRepository.findByName("ripple")).thenReturn(Optional.of(ripple));
//        when(requestHeadersSpec.retrieve()).thenReturn(mock(RestClient.ResponseSpec.class)); // Mock further if necessary
//
//        coinService.updateRippleData();
//
//        verify(coinPriceRepository).save(ripple);
//        assertEquals(2, ripple.getUsd());
//    }
//
//    @Test
//    void updateBitcoinData_ShouldCreateNewCoinPrice() {
//        BitcoinDTO bitcoinDTO = new BitcoinDTO(new CoinPriceEntity(Double.valueOf(40000), "bitcoin"));
//        when(coinPriceRepository.findByName("bitcoin")).thenReturn(Optional.empty());
//        when(requestHeadersSpec.retrieve()).thenReturn(mock(RestClient.ResponseSpec.class)); // Mock further if necessary
//
//        coinService.updateBitcoinData();
//
//        verify(coinPriceRepository).save(any(CoinPriceEntity.class));
//    }
//
//    @Test
//    void updateBitcoinData_ShouldNotUpdateWhenPriceUnchanged() {
//        CoinPriceEntity bitcoin = new CoinPriceEntity(40000, "bitcoin");
//        BitcoinDTO bitcoinDTO = new BitcoinDTO(new CoinPriceEntity(Double.valueOf(40000), "bitcoin"));
//        when(coinPriceRepository.findByName("bitcoin")).thenReturn(Optional.of(bitcoin));
//        when(requestHeadersSpec.retrieve()).thenReturn(mock(RestClient.ResponseSpec.class)); // Mock further if necessary
//
//        coinService.updateBitcoinData();
//
//        verify(coinPriceRepository, never()).save(any(CoinPriceEntity.class));
//    }
}
