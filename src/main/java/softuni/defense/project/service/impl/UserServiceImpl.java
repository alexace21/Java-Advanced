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

        // TODO: Set Role
        Optional<UserRoleEntity> optionalRole = this.userRoleRepository.findByRole(UserRoleEnum.USER);

        if (optionalUser.isPresent()) {
            user.setRoles(List.of(optionalRole.get()));
        }
        UserEntity savedUser = userRepository.save(user);

        this.changeLogService.createUserChangeLog(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto loginUser(UserLoginDTO loginDTO) {
        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return modelMapper.map(user, UserDto.class);
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDto findByEmail(String login) {
        UserEntity user = userRepository.findByEmail(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return modelMapper.map(user, UserDto.class);
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);

        mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        return mappedEntity;
    }
}
