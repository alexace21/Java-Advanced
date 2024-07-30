package softuni.defense.project.service;

import softuni.defense.project.model.dtos.UserLoginDTO;
import softuni.defense.project.model.dtos.UserRegistrationDTO;

public interface UserService {
    void registerUser(UserRegistrationDTO registerDTO);

    void loginUser(UserLoginDTO registerDTO);
}
