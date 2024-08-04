package softuni.defense.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.defense.project.model.dtos.FeedbackDto;
import softuni.defense.project.model.dtos.FeedbackLogDTO;
import softuni.defense.project.model.entities.FeedbackChangeLogEntity;
import softuni.defense.project.model.entities.FeedbackEntity;
import softuni.defense.project.model.entities.UserEntity;
import softuni.defense.project.model.enums.FeedbackStatusEnum;
import softuni.defense.project.model.enums.TypeChangeEnum;
import softuni.defense.project.repositories.FeedbackRepository;
import softuni.defense.project.repositories.UserRepository;
import softuni.defense.project.service.ChangeLogService;
import softuni.defense.project.service.FeedbackService;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ChangeLogService changeLogService;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserRepository userRepository, ModelMapper modelMapper, ChangeLogService changeLogService) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.changeLogService = changeLogService;
    }

    @Override
    public FeedbackDto submitUserFeedback(FeedbackDto feedbackDto) {
        Optional<UserEntity> optionalUser = this.userRepository.findByEmail(feedbackDto.getOwnerUser());

        if (optionalUser.isPresent()) {
            FeedbackEntity feedback = this.modelMapper.map(feedbackDto, FeedbackEntity.class);
            feedback.setOwnerUser(optionalUser.get());

            feedback.setStatus(FeedbackStatusEnum.NEW);
            feedback.setSubmitDate(LocalDate.now());

            FeedbackEntity savedFeedback = this.feedbackRepository.save(feedback);
            this.changeLogService.createFeedbackChangeLog(savedFeedback);

            return this.modelMapper.map(savedFeedback, FeedbackDto.class);
        }

        return null;
    }

    @Override
    public FeedbackLogDTO resolveFeedback(String id) {
        Optional<FeedbackChangeLogEntity> optionalFeedbackChangeLog = this.changeLogService.findFeedbackLogById(Long.valueOf(id));

        if (optionalFeedbackChangeLog.isPresent()) {
            FeedbackChangeLogEntity feedbackLog = optionalFeedbackChangeLog.get();

            Optional<FeedbackEntity> optionalFeedbackEntity = this.feedbackRepository.findById(feedbackLog.getFeedback().getId());

            if (optionalFeedbackChangeLog.isPresent()) {
                FeedbackEntity feedback = optionalFeedbackEntity.get();

                feedback.setStatus(FeedbackStatusEnum.RESOLVED);
                this.feedbackRepository.save(feedback);

                feedbackLog.setTypeChange(TypeChangeEnum.STATUS);
                feedbackLog.setOldValue(FeedbackStatusEnum.NEW.toString());
                feedbackLog.setNewValue(FeedbackStatusEnum.RESOLVED.toString());
                this.changeLogService.updateFeedbackLog(feedbackLog);

                return mapToLogDTO(feedbackLog);
            }
        }
        throw new RuntimeException("Feedback is corrupt!");
    }

    @Override
    public FeedbackLogDTO removeFeedback(String id) {
        Optional<FeedbackChangeLogEntity> optionalFeedbackChangeLog = this.changeLogService.findFeedbackLogById(Long.valueOf(id));

        if (optionalFeedbackChangeLog.isPresent()) {
            FeedbackChangeLogEntity feedbackLog = optionalFeedbackChangeLog.get();

            Optional<FeedbackEntity> optionalFeedbackEntity = this.feedbackRepository.findById(feedbackLog.getFeedback().getId());

            if (optionalFeedbackChangeLog.isPresent()) {
                FeedbackEntity feedback = optionalFeedbackEntity.get();

                feedback.setStatus(FeedbackStatusEnum.REMOVED);
                this.feedbackRepository.saveAndFlush(feedback);

                feedbackLog.setTypeChange(TypeChangeEnum.STATUS);
                feedbackLog.setOldValue(FeedbackStatusEnum.NEW.toString());
                feedbackLog.setNewValue(FeedbackStatusEnum.REMOVED.toString());
                this.changeLogService.updateFeedbackLog(feedbackLog);

                return mapToLogDTO(feedbackLog);
            }
        }
        throw new RuntimeException("Feedback is corrupt!");
    }

    private FeedbackLogDTO mapToLogDTO(FeedbackChangeLogEntity feedbackEntity) {

        FeedbackLogDTO logDTO = new FeedbackLogDTO(
                feedbackEntity.getId().toString(),
                feedbackEntity.getNewValue().toString(),
                feedbackEntity.getOwner().getFirstName(),
                null,
                feedbackEntity.getOwner().getEmail(),
                feedbackEntity.getSatisfaction().toString(),
                feedbackEntity.getRecommendation(),
                feedbackEntity.getSubmitDate().toString()
        );

        return logDTO;
    }
}
