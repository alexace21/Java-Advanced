package softuni.defense.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.defense.project.model.dtos.FeedbackDto;
import softuni.defense.project.model.entities.FeedbackEntity;
import softuni.defense.project.model.entities.UserEntity;
import softuni.defense.project.model.enums.FeedbackStatusEnum;
import softuni.defense.project.repositories.FeedbackRepository;
import softuni.defense.project.repositories.UserRepository;
import softuni.defense.project.service.FeedbackService;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
            return this.modelMapper.map(savedFeedback, FeedbackDto.class);
        }

        return null;
    }
}
