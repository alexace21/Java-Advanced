package softuni.defense.project.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import softuni.defense.project.model.entities.base.BaseEntity;

import java.io.Serializable;

@Entity
@Table(name = "crypto_coins")
public class CoinPriceEntity extends BaseEntity implements Serializable {

    private double usd;
    private String name;


    public CoinPriceEntity() {
    }

    public CoinPriceEntity(double usd, String name) {
        this.usd = usd;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }
}
