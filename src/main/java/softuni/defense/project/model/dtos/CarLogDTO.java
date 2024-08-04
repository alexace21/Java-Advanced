package softuni.defense.project.model.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

public class CarLogDTO {
    private String id;

    private String make;

    private String model;

    private String userOwner;
    private String price;
    private String submitDate;

    private String status;
    public CarLogDTO() {
    }

    public CarLogDTO(String id, String make, String model, String userOwner, String price, String submitDate, String status) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.userOwner = userOwner;
        this.price = price;
        this.submitDate = submitDate;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }
}
