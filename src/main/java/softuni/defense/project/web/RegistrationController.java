package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.config.UserAuthProvider;
import softuni.defense.project.model.dtos.UserDto;
import softuni.defense.project.model.dtos.UserRegistrationDTO;
import softuni.defense.project.service.UserService;

import java.net.URI;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    public RegistrationController(UserService userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRegistrationDTO registerDTO) {
        UserDto user = userService.registerUser(registerDTO);
        user.setToken(userAuthProvider.createToken(user.getEmail()));

        return ResponseEntity.created(URI.create("/users/" + user.getId()))
                .body(user);
    }
}
