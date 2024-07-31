package softuni.defense.project.model.dtos;

public class UserDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String token;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String login, String token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = login;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setEmail(String login) {
        this.email = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
