package softuni.defense.project.service;

import softuni.defense.project.model.entities.CoinPriceEntity;

import java.util.List;

public interface CryptoService {
    List<CoinPriceEntity> getRippleAndBitcoinData();
}
