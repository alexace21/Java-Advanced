package softuni.defense.project.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private ChangeLogService changeLogService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserLoginDTO loginDTO;
    private UserRegistrationDTO registerDTO;
    private UserEntity userEntity;
    private UserRoleEntity userRoleEntity;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        loginDTO = new UserLoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("password");

        registerDTO = new UserRegistrationDTO();
        registerDTO.setEmail("test@example.com");
        registerDTO.setPassword("topsecret123");

        userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRoleEnum.USER);

        userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("encodedPassword");
        userEntity.setRoles(List.of(userRoleEntity));



        userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setRole(UserRoleEnum.USER);
    }

    @Test
    void registerUser_ShouldRegisterUser_WhenUserDoesNotExist() {
        when(userRepository.findByEmail(registerDTO.getEmail())).thenReturn(Optional.empty());
        when(modelMapper.map(registerDTO, UserEntity.class)).thenReturn(userEntity);
        when(passwordEncoder.encode(registerDTO.getPassword())).thenReturn("encodedPassword");
        when(userRoleRepository.findByRole(UserRoleEnum.USER)).thenReturn(Optional.of(userRoleEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(modelMapper.map(userEntity, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.registerUser(registerDTO);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals(UserRoleEnum.USER, result.getRole());

        verify(userRepository).findByEmail(registerDTO.getEmail());
        verify(modelMapper).map(registerDTO, UserEntity.class);
        verify(passwordEncoder).encode(registerDTO.getPassword());
        verify(userRoleRepository).findByRole(UserRoleEnum.USER);
        verify(userRepository).save(userEntity);
        verify(changeLogService).createUserChangeLog(userEntity);
        verify(modelMapper).map(userEntity, UserDto.class);
    }

    @Test
    void registerUser_ShouldThrowException_WhenUserAlreadyExists() {
        when(userRepository.findByEmail(registerDTO.getEmail())).thenReturn(Optional.of(userEntity));

        AppException exception = assertThrows(AppException.class, () -> {
            userService.registerUser(registerDTO);
        });

        assertEquals("User already exists", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());

        verify(userRepository).findByEmail(registerDTO.getEmail());
        verify(modelMapper, never()).map(registerDTO, UserEntity.class);
        verify(passwordEncoder, never()).encode(registerDTO.getPassword());
        verify(userRoleRepository, never()).findByRole(UserRoleEnum.USER);
        verify(userRepository, never()).save(userEntity);
        verify(changeLogService, never()).createUserChangeLog(userEntity);
        verify(modelMapper, never()).map(userEntity, UserDto.class);
    }

    @Test
    void findByEmail_ShouldReturnUserDto_WhenUserExists() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(userEntity, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.findByEmail("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals(UserRoleEnum.USER, result.getRole());

        verify(userRepository).findByEmail("test@example.com");
        verify(modelMapper).map(userEntity, UserDto.class);
    }

    @Test
    void findByEmail_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> {
            userService.findByEmail("test@example.com");
        });

        assertEquals("Unknown user", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getCode());

        verify(userRepository).findByEmail("test@example.com");
        verify(modelMapper, never()).map(any(UserEntity.class), eq(UserDto.class));
    }

    @Test
    void loginUser_ShouldReturnUserDto_WhenCredentialsAreValid() {
        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())).thenReturn(true);
        when(modelMapper.map(userEntity, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.loginUser(loginDTO);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals(UserRoleEnum.USER, result.getRole());

        verify(userRepository).findByEmail(loginDTO.getEmail());
        verify(passwordEncoder).matches(loginDTO.getPassword(), userEntity.getPassword());
        verify(modelMapper).map(userEntity, UserDto.class);
    }

    @Test
    void loginUser_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> {
            userService.loginUser(loginDTO);
        });

        assertEquals("Unknown user", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getCode());

        verify(userRepository).findByEmail(loginDTO.getEmail());
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(modelMapper, never()).map(any(UserEntity.class), eq(UserDto.class));
    }

    @Test
    void loginUser_ShouldThrowException_WhenPasswordIsInvalid() {
        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())).thenReturn(false);

        AppException exception = assertThrows(AppException.class, () -> {
            userService.loginUser(loginDTO);
        });

        assertEquals("Invalid password", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());

        verify(userRepository).findByEmail(loginDTO.getEmail());
        verify(passwordEncoder).matches(loginDTO.getPassword(), userEntity.getPassword());
        verify(modelMapper, never()).map(any(UserEntity.class), eq(UserDto.class));
    }
}
