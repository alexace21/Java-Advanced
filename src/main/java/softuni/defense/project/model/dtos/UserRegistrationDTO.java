package softuni.defense.project.model.dtos;

public class UserRegistrationDTO {

    private String email;

    private String password;

    public UserRegistrationDTO() {
    }


    public UserRegistrationDTO(String email, String password, String login) {
        this.email = email;
        this.password = password;
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
