package softuni.defense.project.model.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import softuni.defense.project.model.entities.UserEntity;

import java.time.LocalDate;

public class UserLogDTO {

    private String email;
    private String firstName;
    private String lastName;

    private String registeredDate;
    private String role;

    public UserLogDTO() {
    }

    public UserLogDTO(String email, String firstName, String lastName, String registeredDate, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registeredDate = registeredDate;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
