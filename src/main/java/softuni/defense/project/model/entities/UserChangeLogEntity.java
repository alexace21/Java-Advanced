package softuni.defense.project.model.entities;

import jakarta.persistence.*;
import softuni.defense.project.model.entities.base.BaseEntity;
import softuni.defense.project.model.enums.TypeChangeEnum;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_change")
    private TypeChangeEnum typeChange;
    @Column(name = "old_value")
    private String oldValue;
    @Column(name = "new_value")
    private String newValue;

    public UserChangeLogEntity() {
    }

    public UserChangeLogEntity(String email, String firstName, String lastName, LocalDate registeredDate, UserEntity userId, String role, TypeChangeEnum typeChange, String oldValue, String newValue) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registeredDate = registeredDate;
        this.userId = userId;
        this.role = role;
        this.typeChange = typeChange;
        this.oldValue = oldValue;
        this.newValue = newValue;
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
