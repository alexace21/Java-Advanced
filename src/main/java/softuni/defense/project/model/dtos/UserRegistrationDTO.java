package softuni.defense.project.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password is required!")
    @Size(min = 8, message = "Password should be  minimum 8 characters long!")
    private String password;
    @NotBlank(message = "First Name is required!")
    @Size(min = 2, message = "First name should be a least 2 characters long!")
    private String firstName;
    @NotBlank(message = "Last Name is required!")
    @Size(min = 2, message = "Last name should be a least 2 characters long!")
    private String lastName;

    public UserRegistrationDTO() {
    }


    public UserRegistrationDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserRegistrationDTO(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
