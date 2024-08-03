package softuni.defense.project.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import softuni.defense.project.model.entities.base.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name = "user_change_log")
public class UserChangeLogEntity extends BaseEntity {
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "register_date")

    private LocalDate registeredDate;
    @OneToOne
    private UserEntity userId;

    private String role;
    public UserChangeLogEntity() {
    }

    public UserChangeLogEntity(String email, String firstName, String lastName, LocalDate registeredDate, UserEntity userId, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registeredDate = registeredDate;
        this.userId = userId;
        this.role = role;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}
