package softuni.defense.project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import softuni.defense.project.config.CoinGeckoConfig;
import softuni.defense.project.model.entities.CoinPriceEntity;
import softuni.defense.project.model.dtos.BitcoinDTO;
import softuni.defense.project.model.dtos.RippleCoinDTO;
import softuni.defense.project.repositories.CoinPriceRepository;
import softuni.defense.project.service.CryptoService;

import java.util.List;
import java.util.Optional;

@Service
public class CryptoServiceImpl implements CryptoService {

    private Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);
    private final RestClient cryptoRestClient;
    private final CoinPriceRepository coinPriceRepository;
    private final CoinGeckoConfig cryptoConfig;

    public CryptoServiceImpl(@Qualifier("coinGeckoRestClient") RestClient cryptoRestClient, CoinPriceRepository coinPriceRepository, CoinGeckoConfig cryptoConfig) {
        this.cryptoRestClient = cryptoRestClient;
        this.coinPriceRepository = coinPriceRepository;
        this.cryptoConfig = cryptoConfig;
    }


    @Override
    public List<CoinPriceEntity> getRippleAndBitcoinData() {
        return this.coinPriceRepository.findAll();
    }

    // 0 * * * * <-- 1 hour
    // 0 0 */1 * * * <-- 1 hour Spring CRON
    // */1 * * * * * <-- 1 minute
    @Scheduled(cron = "0 0 */1 * * *", zone = "Europe/Berlin")
    public void updateRippleData() {
        RippleCoinDTO rippleCoinData = fetchRippleData();
        if (rippleCoinData != null) {
            Optional<CoinPriceEntity> optionalCoinEntity = this.coinPriceRepository.findByName("ripple");
            if (optionalCoinEntity.isPresent()) {
                CoinPriceEntity target = optionalCoinEntity.get();

                if (target.getUsd() != rippleCoinData.getRipple().getUsd()) {
                    target.setUsd(rippleCoinData.getRipple().getUsd());

                    this.coinPriceRepository.save(target);
                }
            } else {
                CoinPriceEntity firstRippleCoinEntity = new CoinPriceEntity(rippleCoinData.getRipple().getUsd(), "ripple");
                this.coinPriceRepository.save(firstRippleCoinEntity);
            }
        }
    }


    @Scheduled(cron = "0 0 */1 * * *", zone = "Europe/Berlin")
    public void updateBitcoinData() {
        BitcoinDTO bitcoinData = fetchBitcoinData();
        if (bitcoinData != null) {
            Optional<CoinPriceEntity> optionalCoinEntity = this.coinPriceRepository.findByName("bitcoin");
            if (optionalCoinEntity.isPresent()) {
                CoinPriceEntity target = optionalCoinEntity.get();

                if (target.getUsd() != bitcoinData.getBitcoin().getUsd()) {
                    target.setUsd(bitcoinData.getBitcoin().getUsd());

                    this.coinPriceRepository.save(target);
                }
            } else {
                CoinPriceEntity firstBitcoinEntity = new CoinPriceEntity(bitcoinData.getBitcoin().getUsd(), "bitcoin");
                this.coinPriceRepository.save(firstBitcoinEntity);
            }
        }
    }

    public BitcoinDTO fetchBitcoinData() {
        LOGGER.info("FETCH Bitcoin Data...");

        StringBuilder preBuildURI = new StringBuilder();
        preBuildURI.append(cryptoConfig.getBaseUrl());

        preBuildURI.append("/simple/price?ids=bitcoin");
        preBuildURI.append("&vs_currencies=usd");

        return cryptoRestClient
                .get()
                .uri(preBuildURI.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

    }
    public RippleCoinDTO fetchRippleData() {
        LOGGER.info("FETCH Ripple Data...");

        StringBuilder preBuildURI = new StringBuilder();
        preBuildURI.append(cryptoConfig.getBaseUrl());

        preBuildURI.append("/simple/price?ids=ripple");
        preBuildURI.append("&vs_currencies=usd");

        return cryptoRestClient
                .get()
                .uri(preBuildURI.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

    }
}
