package softuni.defense.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.defense.project.config.exception.AppException;
import softuni.defense.project.model.dtos.UserDto;
import softuni.defense.project.model.dtos.UserLoginDTO;
import softuni.defense.project.model.dtos.UserRegistrationDTO;
import softuni.defense.project.model.entities.UserEntity;
import softuni.defense.project.model.entities.UserRoleEntity;
import softuni.defense.project.model.enums.UserRoleEnum;
import softuni.defense.project.repositories.UserRepository;
import softuni.defense.project.repositories.UserRoleRepository;
import softuni.defense.project.service.ChangeLogService;
import softuni.defense.project.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ChangeLogService changeLogService;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, ChangeLogService changeLogService, UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.changeLogService = changeLogService;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDto registerUser(UserRegistrationDTO registerDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(registerDTO.getEmail());

        if (optionalUser.isPresent()) {
            throw new AppException("User already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = modelMapper.map(registerDTO, UserEntity.class);

        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Optional<UserRoleEntity> optionalRole = this.userRoleRepository.findByRole(UserRoleEnum.USER);

        if (optionalRole.isPresent()) {
            user.setRoles(List.of(optionalRole.get()));
        }

        UserEntity savedUser = userRepository.save(user);

        this.changeLogService.createUserChangeLog(savedUser);
        UserDto outputUser = modelMapper.map(savedUser, UserDto.class);
        outputUser.setRole(savedUser.getRoles().get(0).getRole());

        return outputUser;
    }

    @Override
    public UserDto loginUser(UserLoginDTO loginDTO) {
        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            UserDto outputUser = modelMapper.map(user, UserDto.class);
            outputUser.setRole(user.getRoles().get(0).getRole());
            return outputUser;
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDto findByEmail(String login) {
        UserEntity user = userRepository.findByEmail(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        UserDto output = modelMapper.map(user, UserDto.class);
        output.setRole(user.getRoles().get(0).getRole());
        return output;
    }

}
