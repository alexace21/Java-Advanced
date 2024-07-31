package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.config.UserAuthProvider;
import softuni.defense.project.model.dtos.UserDto;
import softuni.defense.project.model.dtos.UserLoginDTO;
import softuni.defense.project.service.UserService;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    public LoginController(UserService userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<UserDto> login(@RequestBody UserLoginDTO loginDTO) {
        UserDto user = userService.loginUser(loginDTO);
        user.setToken(userAuthProvider.createToken(user.getEmail()));

        return ResponseEntity.ok(user);
    }
}
