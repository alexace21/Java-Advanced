package softuni.defense.project.model.dtos;

public class CarDTO {

   private  Long id;
   private  Long city_mpg;
   private  Long combination_mpg;
   private  int cylinders;
   private  Double displacement;
   private  String drive;
   private  String fuel_type;
   private  Long highway_mpg;
   private  String make;
   private  String model;
   private  String transmission;
   private  Long year;
   private  String owner;

    public CarDTO() {
    }

    public CarDTO(Long id, Long city_mpg, Long combination_mpg, int cylinders, Double displacement, String drive, String fuel_type, Long highway_mpg, String make, String model, String transmission, Long year) {
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
