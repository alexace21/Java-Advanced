package softuni.defense.project.service;

import softuni.defense.project.model.dtos.CarLogDTO;
import softuni.defense.project.model.dtos.FeedbackLogDTO;
import softuni.defense.project.model.dtos.UserLogDTO;
import softuni.defense.project.model.entities.FeedbackChangeLogEntity;
import softuni.defense.project.model.entities.FeedbackEntity;
import softuni.defense.project.model.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ChangeLogService {
    void createUserChangeLog(UserEntity savedUser);

    void createFeedbackChangeLog(FeedbackEntity savedFeedback);

    List<FeedbackLogDTO> getAllFeedbackLogs();

    List<UserLogDTO> getAllRegisteredUsers();

    List<CarLogDTO> getAllCarLogs();

    Optional<FeedbackChangeLogEntity> findFeedbackLogById(Long aLong);

    void updateFeedbackLog(FeedbackChangeLogEntity feedbackLog);
}
