package softuni.defense.project.service;

import softuni.defense.project.model.dtos.FeedbackDto;
import softuni.defense.project.model.dtos.FeedbackLogDTO;

public interface FeedbackService {
    FeedbackDto submitUserFeedback(FeedbackDto feedbackDto);

    FeedbackLogDTO resolveFeedback(String id);

    FeedbackLogDTO removeFeedback(String id);
}
