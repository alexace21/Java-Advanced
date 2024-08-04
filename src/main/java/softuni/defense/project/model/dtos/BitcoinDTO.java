package softuni.defense.project.model.dtos;

import softuni.defense.project.model.CoinPriceEntity;

import java.io.Serializable;

public class BitcoinDTO implements Serializable {

    private CoinPriceEntity bitcoin;

    public BitcoinDTO() {
    }

    public BitcoinDTO(CoinPriceEntity bitcoin) {
        this.bitcoin = bitcoin;
    }

    public CoinPriceEntity getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(CoinPriceEntity bitcoin) {
        this.bitcoin = bitcoin;
    }
}
