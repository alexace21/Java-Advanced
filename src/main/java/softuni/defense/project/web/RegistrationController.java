package softuni.defense.project.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.config.UserAuthProvider;
import softuni.defense.project.config.exception.AppException;
import softuni.defense.project.model.dtos.UserDto;
import softuni.defense.project.model.dtos.UserRegistrationDTO;
import softuni.defense.project.service.UserService;
import softuni.defense.project.utils.Validator;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;
    private final Validator validator;

    public RegistrationController(UserService userService, UserAuthProvider userAuthProvider, Validator validator) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
        this.validator = validator;
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRegistrationDTO registerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            validator.validateRegistration(bindingResult);
        }

        UserDto user = userService.registerUser(registerDTO);
        user.setToken(userAuthProvider.createToken(user));

        return ResponseEntity.created(URI.create("/users/" + user.getId()))
                .body(user);
    }
}
