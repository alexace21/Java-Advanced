package softuni.defense.project.service;

import softuni.defense.project.model.dtos.CarLogDTO;
import softuni.defense.project.model.dtos.FeedbackLogDTO;
import softuni.defense.project.model.dtos.UserLogDTO;
import softuni.defense.project.model.entities.FeedbackEntity;
import softuni.defense.project.model.entities.UserEntity;

import java.util.List;

public interface ChangeLogService {
    void createUserChangeLog(UserEntity savedUser);

    void createFeedbackChangeLog(FeedbackEntity savedFeedback);

    List<FeedbackLogDTO> getAllFeedbackLogs();

    List<UserLogDTO> getAllRegisteredUsers();

    List<CarLogDTO> getAllCarLogs();
}
