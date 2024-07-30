package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.UserLoginDTO;
import softuni.defense.project.model.dtos.UserRegistrationDTO;
import softuni.defense.project.service.UserService;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody UserLoginDTO loginDTO) {
        userService.loginUser(loginDTO);
        return ResponseEntity.ok("Successfully Logged IN!");
    }
}
