package softuni.defense.project.service;

import softuni.defense.project.model.dtos.UserDto;
import softuni.defense.project.model.dtos.UserLoginDTO;
import softuni.defense.project.model.dtos.UserRegistrationDTO;

public interface UserService {
    UserDto registerUser(UserRegistrationDTO registerDTO);

    UserDto loginUser(UserLoginDTO registerDTO);

    UserDto findByEmail(String issuer);
}
