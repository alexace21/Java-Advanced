package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.UserRegistrationDTO;
import softuni.defense.project.service.UserService;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity register(@RequestParam UserRegistrationDTO registerDTO) {
        userService.registerUser(registerDTO);
        return ResponseEntity.ok("Successfully Registered!");
    }
}
