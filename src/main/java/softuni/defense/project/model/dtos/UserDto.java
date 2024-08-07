package softuni.defense.project.model.dtos;

import softuni.defense.project.model.enums.UserRoleEnum;

public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String token;

    private UserRoleEnum role;
    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, UserRoleEnum role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public UserDto(String firstName, String lastName, String email, String token, UserRoleEnum role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
        this.role = role;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
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
