package softuni.defense.project.model.dtos;

import softuni.defense.project.model.entities.CoinPriceEntity;

import java.io.Serializable;

public class RippleCoinDTO implements Serializable {

    private CoinPriceEntity ripple;

    public RippleCoinDTO() {
    }

    public RippleCoinDTO(CoinPriceEntity ripple) {
        this.ripple = ripple;
    }

    public CoinPriceEntity getRipple() {
        return ripple;
    }

    public void setRipple(CoinPriceEntity ripple) {
        this.ripple = ripple;
    }
}
