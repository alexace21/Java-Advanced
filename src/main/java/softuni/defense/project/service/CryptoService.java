package softuni.defense.project.service;

import softuni.defense.project.model.CoinPriceEntity;

import java.util.List;

public interface CryptoService {
    List<CoinPriceEntity> getRippleAndBitcoinData();
}
