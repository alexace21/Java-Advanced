package softuni.defense.project.service;

import softuni.defense.project.model.entities.FeedbackEntity;
import softuni.defense.project.model.entities.UserEntity;

public interface ChangeLogService {
    void createUserChangeLog(UserEntity savedUser);

    void createFeedbackChangeLog(FeedbackEntity savedFeedback);
}
