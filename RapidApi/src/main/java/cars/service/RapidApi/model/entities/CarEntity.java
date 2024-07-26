package cars.service.RapidApi.model.entities;

import cars.service.RapidApi.model.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {

    private Long city_mpg;
    private Long combination_mpg;
    private int cylinders;
    private Double displacement;
    private String drive;
    private String fuel_type;
    private Long highway_mpg;
    private String make;
    private String model;
    private String transmission;
    private Long year;


    public Long getCity_mpg() {
        return city_mpg;
    }

    public void setCity_mpg(Long city_mpg) {
        this.city_mpg = city_mpg;
    }

    public Long getCombination_mpg() {
        return combination_mpg;
    }

    public void setCombination_mpg(Long combination_mpg) {
        this.combination_mpg = combination_mpg;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public Double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Double displacement) {
        this.displacement = displacement;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public Long getHighway_mpg() {
        return highway_mpg;
    }

    public void setHighway_mpg(Long highway_mpg) {
        this.highway_mpg = highway_mpg;
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

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }
}
