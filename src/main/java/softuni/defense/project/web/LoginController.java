package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.UserRegistrationDTO;
import softuni.defense.project.service.UserService;

@Controller
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity login(@RequestParam UserRegistrationDTO registerDTO) {
        userService.registerUser(registerDTO);
        return ResponseEntity.ok("Successfully Logged IN!");
    }
}
