package softuni.defense.project.model.dtos;

public class UserRegistrationDTO {

    private String email;

    private String password;

    private String firstName;
    private String lastName;

    public UserRegistrationDTO() {
    }


    public UserRegistrationDTO(String email, String password, String login) {
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
