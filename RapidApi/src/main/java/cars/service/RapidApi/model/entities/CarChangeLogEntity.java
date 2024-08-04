package cars.service.RapidApi.model.entities;

import cars.service.RapidApi.enums.CarStatusEnum;
import cars.service.RapidApi.enums.TypeChangeEnum;
import cars.service.RapidApi.model.entities.base.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "car_change_log")
public class CarChangeLogEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_change")
    private TypeChangeEnum typeChange;
    private String make;
    private String model;

    private String price;
    @Column(name = "user_owner")
    private String userOwner;
    @Column(name = "car_id")
    private Long car;
    @Column(name = "submit_date")
    private LocalDate submitDate;
    @Column(name = "old_value")
    private String oldValue;
    @Column(name = "new_value")
    private String newValue;
    public CarChangeLogEntity() {
    }


    public CarChangeLogEntity(TypeChangeEnum typeChange, String make, String model, String userOwner, Long car, LocalDate submitDate, String oldValue, String newValue, String price) {
        this.typeChange = typeChange;
        this.make = make;
        this.model = model;
        this.userOwner = userOwner;
        this.car = car;
        this.submitDate = submitDate;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public TypeChangeEnum getTypeChange() {
        return typeChange;
    }

    public void setTypeChange(TypeChangeEnum typeChange) {
        this.typeChange = typeChange;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
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
