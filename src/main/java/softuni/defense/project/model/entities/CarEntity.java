package softuni.defense.project.model.entities;

import jakarta.persistence.*;
import softuni.defense.project.model.enums.CarStatusEnum;
import softuni.defense.project.model.enums.EngineTypeEnum;
import softuni.defense.project.model.enums.TransmissionTypeEnum;
import softuni.defense.project.model.entities.base.BaseEntity;

@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {
    @ManyToOne
    private UserEntity owner;
    @Column(name = "vehicle_identification_number", unique = true)
    private String VIN;

    private String model;
    private String make;
    private Integer year;
    @Enumerated(EnumType.STRING)
    private TransmissionTypeEnum transmission;

    @Enumerated(EnumType.STRING)
    private CarStatusEnum status;

    private String description;

    private Integer mileage;

    private int price;

    @Enumerated(EnumType.STRING)
    private EngineTypeEnum engine;


    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public TransmissionTypeEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionTypeEnum transmission) {
        this.transmission = transmission;
    }

    public CarStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CarStatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public EngineTypeEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineTypeEnum engine) {
        this.engine = engine;
    }
}
