package softuni.defense.project.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import softuni.defense.project.model.entities.base.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "ex_rates")
public class ExRateEntity extends BaseEntity {

    @Column(unique = true)
    private String currency;
    private BigDecimal rate;

    public String getCurrency() {
        return currency;
    }

    public ExRateEntity setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public ExRateEntity setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    @Override
    public String toString() {
        return "ExRateEntity{" +
                "currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }
}
