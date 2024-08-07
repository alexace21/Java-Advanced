package softuni.defense.project.model.dtos;

import jakarta.validation.constraints.*;

public class CarDTO {

   private  Long id;
   @NotNull(message = "City Consumption is required!")
   @Min(value = 1, message = "City Consumption value should be minimum 1!")
   @Positive(message = "City Consumption should be positive a number!")
   private  Long city_mpg;
    @NotNull(message = "Combined Consumption is required!")
    @Min(value = 1, message = "Combined Consumption value should be minimum 1")
    @Positive(message = "Combined Consumption should be a positive number!")
   private  Long combination_mpg;
    @NotNull(message = "Car Cylinders are required!")
    @Min(value = 2, message = "Car Cylinders value should be minimum 2")
    @Positive(message = "Car Cylinders should be a positive number!")
   private  int cylinders;
    @NotNull(message = "Car Displacement is required!")
    @Positive(message = "Car Displacement should be a positive number!")
   private  Double displacement;
    @NotBlank(message = "Car Drive Type is required!")
   private  String drive;
    @NotBlank(message = "Car Fuel Type is required!")
   private  String fuel_type;
    @NotNull(message = "Highway Consumption is required!")
    @Min(value = 1, message = "Highway Consumption value should be minimum 1")
    @Positive(message = "Highway Consumption should be a positive number!")
   private  Long highway_mpg;
    @NotBlank(message = "Car Make is required!")
   private  String make;
    @NotBlank(message = "Car Model is required!")
   private  String model;
    @NotBlank(message = "Car Transmission Type is required!")
   private  String transmission;
    @NotNull(message = "Car Year is required!")
    @Min(value = 1960, message = "Car Year value should be above 1960!")
    @Positive(message = "Car Year value should be a positive number!")
    @Max(value = 2024, message = "Car Year value should not exceed 2024!")
   private  Long year;
    @NotBlank(message = "Car Price is required!")
   private String price;
    @NotBlank(message = "Car Owner is required!")
   private  String owner;


    public CarDTO() {
    }

    public CarDTO(Long id, Long city_mpg, Long combination_mpg, int cylinders, Double displacement, String drive, String fuel_type, Long highway_mpg, String make, String model, String transmission, Long year, String price, String owner) {
        this.id = id;
        this.city_mpg = city_mpg;
        this.combination_mpg = combination_mpg;
        this.cylinders = cylinders;
        this.displacement = displacement;
        this.drive = drive;
        this.fuel_type = fuel_type;
        this.highway_mpg = highway_mpg;
        this.make = make;
        this.model = model;
        this.transmission = transmission;
        this.year = year;
        this.price = price;
        this.owner = owner;
    }

    public CarDTO(Long city_mpg, Long combination_mpg, int cylinders, Double displacement, String drive, String fuel_type, Long highway_mpg, String make, String model, String transmission, Long year, String price, String owner) {
        this.city_mpg = city_mpg;
        this.combination_mpg = combination_mpg;
        this.cylinders = cylinders;
        this.displacement = displacement;
        this.drive = drive;
        this.fuel_type = fuel_type;
        this.highway_mpg = highway_mpg;
        this.make = make;
        this.model = model;
        this.transmission = transmission;
        this.year = year;
        this.price = price;
        this.owner = owner;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
