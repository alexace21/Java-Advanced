package softuni.defense.project.service.impl;

import softuni.defense.project.model.dtos.FeedbackDto;
import softuni.defense.project.repositories.FeedbackRepository;
import softuni.defense.project.service.FeedbackService;

public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public FeedbackDto submitUserFeedback(FeedbackDto feedbackDto) {
        return null;
    }
}
