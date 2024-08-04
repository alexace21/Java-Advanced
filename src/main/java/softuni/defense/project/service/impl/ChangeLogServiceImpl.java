package softuni.defense.project.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import softuni.defense.project.model.dtos.*;
import softuni.defense.project.model.entities.FeedbackChangeLogEntity;
import softuni.defense.project.model.entities.FeedbackEntity;
import softuni.defense.project.model.entities.UserChangeLogEntity;
import softuni.defense.project.model.entities.UserEntity;
import softuni.defense.project.model.enums.FeedbackRatingEnum;
import softuni.defense.project.model.enums.FeedbackStatusEnum;
import softuni.defense.project.model.enums.TypeChangeEnum;
import softuni.defense.project.repositories.FeedbackChangeLogRepository;
import softuni.defense.project.repositories.UserChangeLogRepository;
import softuni.defense.project.service.ChangeLogService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChangeLogServiceImpl implements ChangeLogService {

    private final FeedbackChangeLogRepository feedbackChangeLogRepository;
    private final UserChangeLogRepository userChangeLogRepository;
    private final RestClient carRestClient;

    public ChangeLogServiceImpl(FeedbackChangeLogRepository feedbackChangeLogRepository, UserChangeLogRepository userChangeLogRepository, RestClient carRestClient) {
        this.feedbackChangeLogRepository = feedbackChangeLogRepository;
        this.userChangeLogRepository = userChangeLogRepository;
        this.carRestClient = carRestClient;
    }


    @Override
    public void createUserChangeLog(UserEntity savedUser) {
        UserChangeLogEntity userLog = new UserChangeLogEntity(
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getFirstName(),
                LocalDate.now(),
                savedUser,
                "USER",
                TypeChangeEnum.CREATION,
                null,
                "NEW"
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
                TypeChangeEnum.CREATION,
                savedFeedback,
                savedFeedback.getOwnerUser(),
                satisfaction,
                savedFeedback.getRecommendOption(), 
                LocalDate.now(),
                null,
                FeedbackStatusEnum.NEW.toString()
        );

        this.feedbackChangeLogRepository.save(feedbackLog);
    }

    @Override
    public List<FeedbackLogDTO> getAllFeedbackLogs() {
        return this.feedbackChangeLogRepository.findAll()
                        .stream()
                .filter(x -> x.getNewValue().equals(FeedbackStatusEnum.NEW.toString()))
                                .map(this::mapToLogDTO)
                                        .collect(Collectors.toList());
    }

    @Override
    public List<UserLogDTO> getAllRegisteredUsers() {
        return this.userChangeLogRepository.findAll()
                .stream()
                .map(this::mapToLogDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<CarLogDTO> getAllCarLogs() {
        StringBuilder preBuildURI = new StringBuilder();
        preBuildURI.append("/change-log/cars");

        return carRestClient
                .get()
                .uri(preBuildURI.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

    }

    @Override
    public Optional<FeedbackChangeLogEntity> findFeedbackLogById(Long id) {
        return this.feedbackChangeLogRepository.findById(id);
    }

    @Override
    public void updateFeedbackLog(FeedbackChangeLogEntity feedbackLog) {
        this.feedbackChangeLogRepository.save(feedbackLog);
    }

    private FeedbackLogDTO mapToLogDTO(FeedbackChangeLogEntity feedbackChangeLog) {
        FeedbackLogDTO logDTO = new FeedbackLogDTO(
                feedbackChangeLog.getId().toString(),
                feedbackChangeLog.getNewValue().toString(),
                feedbackChangeLog.getOwner().getFirstName(),
                null,
                feedbackChangeLog.getOwner().getEmail(),
                feedbackChangeLog.getSatisfaction().toString(),
                feedbackChangeLog.getRecommendation(),
                feedbackChangeLog.getSubmitDate().toString()
        );
        return logDTO;
    }

    private UserLogDTO mapToLogDTO(UserChangeLogEntity userChangeLog) {
        UserLogDTO logDTO = new UserLogDTO(
                userChangeLog.getEmail(),
                userChangeLog.getFirstName(),
                userChangeLog.getLastName(),
                userChangeLog.getRegisteredDate().toString(),
                userChangeLog.getRole()
        );
        return logDTO;
    }
}
