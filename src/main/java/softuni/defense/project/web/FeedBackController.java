package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.CarDTO;
import softuni.defense.project.model.dtos.FeedbackDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/feedback")
@CrossOrigin(origins = "http://localhost:3000")
public class FeedBackController {


    @PostMapping
    @ResponseBody
    public ResponseEntity submitUserFeedback(
            @RequestBody FeedbackDto feedbackDto
    ) {
        System.out.println(feedbackDto);
        return ResponseEntity.ok("Thank you for submitting your feedback!");
    }
}
