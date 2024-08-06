package softuni.defense.project.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import softuni.defense.project.model.dtos.FeedbackDto;
import softuni.defense.project.model.dtos.FeedbackLogDTO;
import softuni.defense.project.service.FeedbackService;
import softuni.defense.project.utils.Validator;

@Controller
@RequestMapping("/feedback")
@CrossOrigin(origins = "http://localhost:3000")
public class FeedBackController {

    private final FeedbackService feedbackService;
    private final Validator validator;

    public FeedBackController(FeedbackService feedbackService, Validator validator) {
        this.feedbackService = feedbackService;
        this.validator = validator;
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<String> submitUserFeedback( @Valid
            @RequestBody FeedbackDto feedbackDto, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            validator.validateFeedback(bindingResult);
        }
        this.feedbackService.submitUserFeedback(feedbackDto);
        return ResponseEntity.ok("Thank you for submitting your feedback!");
    }

    @PostMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FeedbackLogDTO> resolveFeedback(@PathVariable String id) {
        return ResponseEntity.ok(this.feedbackService.resolveFeedback(id));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FeedbackLogDTO> removeFeedback(@PathVariable String id) {
        return ResponseEntity.ok(this.feedbackService.removeFeedback(id));
    }
}
