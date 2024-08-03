package softuni.defense.project.service.impl;

import org.springframework.stereotype.Service;
import softuni.defense.project.model.entities.FeedbackChangeLogEntity;
import softuni.defense.project.model.entities.FeedbackEntity;
import softuni.defense.project.model.entities.UserChangeLogEntity;
import softuni.defense.project.model.entities.UserEntity;
import softuni.defense.project.model.enums.FeedbackRatingEnum;
import softuni.defense.project.model.enums.FeedbackStatusEnum;
import softuni.defense.project.repositories.FeedbackChangeLogRepository;
import softuni.defense.project.repositories.UserChangeLogRepository;
import softuni.defense.project.service.ChangeLogService;

import java.time.LocalDate;

@Service
public class ChangeLogServiceImpl implements ChangeLogService {

    private final FeedbackChangeLogRepository feedbackChangeLogRepository;
    private final UserChangeLogRepository userChangeLogRepository;

    public ChangeLogServiceImpl(FeedbackChangeLogRepository feedbackChangeLogRepository, UserChangeLogRepository userChangeLogRepository) {
        this.feedbackChangeLogRepository = feedbackChangeLogRepository;
        this.userChangeLogRepository = userChangeLogRepository;
    }


    @Override
    public void createUserChangeLog(UserEntity savedUser) {
        UserChangeLogEntity userLog = new UserChangeLogEntity(
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getFirstName(),
                LocalDate.now(),
                savedUser,
                "USER"
        );

        this.userChangeLogRepository.save(userLog);
    }

    @Override
    public void createFeedbackChangeLog(FeedbackEntity savedFeedback) {

        FeedbackRatingEnum satisfaction = null;

        switch (savedFeedback.getSelectedOption()) {
            case "very_satisfied": satisfaction = FeedbackRatingEnum.THE_BEST;
                break;
            case "satisfied": satisfaction = FeedbackRatingEnum.SATISFIED;
                break;
            case "neutral": satisfaction = FeedbackRatingEnum.OKAY;
                break;
            case "dissatisfied": satisfaction = FeedbackRatingEnum.NOT_HAPPY;
                break;
            case "very_dissatisfied": satisfaction = FeedbackRatingEnum.DISAPPOINTING;
                break;
        }
        FeedbackChangeLogEntity feedbackLog = new FeedbackChangeLogEntity(
                FeedbackStatusEnum.NEW,
                savedFeedback.getOwnerUser(),
                satisfaction,
                savedFeedback.getRecommendOption(), 
                LocalDate.now()
        );

        this.feedbackChangeLogRepository.save(feedbackLog);
    }
}
