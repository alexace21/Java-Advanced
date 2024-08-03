package cars.service.RapidApi.model.entities;

import cars.service.RapidApi.model.entities.base.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "car_change_log")
public class CarChangeLogEntity extends BaseEntity {

    private String make;

    private String model;

    @Column(name = "user_owner")
    private String userOwner;

    @OneToOne
    private CarEntity car;

    @Column(name = "submit_date")
    private LocalDate submitDate;
    public CarChangeLogEntity() {
    }


    public CarChangeLogEntity(String make, String model, String userOwner, CarEntity car, LocalDate submitDate) {
        this.make = make;
        this.model = model;
        this.userOwner = userOwner;
        this.car = car;
        this.submitDate = submitDate;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(String userOwner) {
        this.userOwner = userOwner;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }
}
