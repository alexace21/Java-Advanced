package softuni.defense.project.service;

import softuni.defense.project.model.dtos.FeedbackDto;

public interface FeedbackService {
    FeedbackDto submitUserFeedback(FeedbackDto feedbackDto);
}
