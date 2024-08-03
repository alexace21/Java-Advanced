package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.FeedbackDto;
import softuni.defense.project.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
@CrossOrigin(origins = "http://localhost:3000")
public class FeedBackController {

    private final FeedbackService feedbackService;

    public FeedBackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity submitUserFeedback(
            @RequestBody FeedbackDto feedbackDto
    ) {
        this.feedbackService.submitUserFeedback(feedbackDto);
        return ResponseEntity.ok("Thank you for submitting your feedback!");
    }
}
