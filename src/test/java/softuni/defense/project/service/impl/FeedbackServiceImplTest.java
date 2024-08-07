package softuni.defense.project.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import softuni.defense.project.config.exception.AppException;
import softuni.defense.project.model.dtos.FeedbackDto;
import softuni.defense.project.model.dtos.FeedbackLogDTO;
import softuni.defense.project.model.entities.FeedbackChangeLogEntity;
import softuni.defense.project.model.entities.FeedbackEntity;
import softuni.defense.project.model.entities.UserEntity;
import softuni.defense.project.model.enums.FeedbackRatingEnum;
import softuni.defense.project.model.enums.FeedbackStatusEnum;
import softuni.defense.project.model.enums.TypeChangeEnum;
import softuni.defense.project.repositories.FeedbackRepository;
import softuni.defense.project.repositories.UserRepository;
import softuni.defense.project.service.ChangeLogService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private ChangeLogService changeLogService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    private FeedbackDto feedbackDto;
    private UserEntity userEntity;
    private FeedbackEntity feedbackEntity;

    private FeedbackChangeLogEntity feedbackChangeLogEntity;
    private FeedbackLogDTO feedbackLogDTO;

    private FeedbackLogDTO removedFeedbackLogDTO;

    @BeforeEach
    void setUp() {
        feedbackDto = new FeedbackDto();
        feedbackDto.setOwnerUser("test@example.com");

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("test@example.com");
        userEntity.setFirstName("Axel");


        feedbackEntity = new FeedbackEntity();
        feedbackEntity.setId(1L);
        feedbackEntity.setOwnerUser(userEntity);
        feedbackEntity.setStatus(FeedbackStatusEnum.NEW);
        feedbackEntity.setSubmitDate(LocalDate.now());


        feedbackChangeLogEntity = new FeedbackChangeLogEntity();
        feedbackChangeLogEntity.setId(1L);
        feedbackChangeLogEntity.setOwner(userEntity);
        feedbackChangeLogEntity.setOldValue(FeedbackStatusEnum.NEW.toString());
        feedbackChangeLogEntity.setNewValue(FeedbackStatusEnum.RESOLVED.toString());
        feedbackChangeLogEntity.setTypeChange(TypeChangeEnum.STATUS);
        feedbackChangeLogEntity.setFeedback(feedbackEntity);
        feedbackChangeLogEntity.setSatisfaction(FeedbackRatingEnum.SATISFIED);
        feedbackChangeLogEntity.setRecommendation("Probably");
        feedbackChangeLogEntity.setSubmitDate(LocalDate.now());

        feedbackLogDTO = new FeedbackLogDTO(
                "1", // id
                FeedbackStatusEnum.RESOLVED.toString(), // newValue
                "Axel", // ownerFirstName
                null, // ownerLastName
                "test@example.com", // ownerEmail
                "SATISFIED", // satisfaction
                "Probably", // recommendation
                LocalDate.now().toString() // submitDate
        );

        removedFeedbackLogDTO = new FeedbackLogDTO(
                "1", // id
                FeedbackStatusEnum.REMOVED.toString(), // newValue
                "Axel", // ownerFirstName
                null, // ownerLastName
                "test@example.com", // ownerEmail
                "SATISFIED", // satisfaction
                "Probably", // recommendation
                LocalDate.now().toString() // submitDate
        );
    }

    @Test
    void submitUserFeedback_ShouldReturnFeedbackDto_WhenUserExists() {
        when(userRepository.findByEmail(feedbackDto.getOwnerUser())).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(feedbackDto, FeedbackEntity.class)).thenReturn(feedbackEntity);
        when(feedbackRepository.save(feedbackEntity)).thenReturn(feedbackEntity);
        FeedbackDto savedFeedbackDto = new FeedbackDto();
        when(modelMapper.map(feedbackEntity, FeedbackDto.class)).thenReturn(savedFeedbackDto);

        FeedbackDto result = feedbackService.submitUserFeedback(feedbackDto);

        assertNotNull(result);

        verify(userRepository).findByEmail(feedbackDto.getOwnerUser());
        verify(modelMapper).map(feedbackDto, FeedbackEntity.class);
        verify(feedbackRepository).save(feedbackEntity);
        verify(changeLogService).createFeedbackChangeLog(feedbackEntity);
        verify(modelMapper).map(feedbackEntity, FeedbackDto.class);
    }

    @Test
    void submitUserFeedback_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findByEmail(feedbackDto.getOwnerUser())).thenReturn(Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> {
            feedbackService.submitUserFeedback(feedbackDto);
        });

        assertEquals("Unauthorized access", exception.getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getCode());

        verify(userRepository).findByEmail(feedbackDto.getOwnerUser());
        verify(modelMapper, never()).map(any(FeedbackDto.class), eq(FeedbackEntity.class));
        verify(feedbackRepository, never()).save(any(FeedbackEntity.class));
        verify(changeLogService, never()).createFeedbackChangeLog(any(FeedbackEntity.class));
        verify(modelMapper, never()).map(any(FeedbackEntity.class), eq(FeedbackDto.class));
    }

    @Test
    void resolveFeedback_ShouldReturnFeedbackLogDTO_WhenFeedbackIsResolved() {
        when(changeLogService.findFeedbackLogById(1L)).thenReturn(Optional.of(feedbackChangeLogEntity));
        when(feedbackRepository.findById(feedbackEntity.getId())).thenReturn(Optional.of(feedbackEntity));

        FeedbackLogDTO result = feedbackService.resolveFeedback("1");

        assertNotNull(result);

        verify(changeLogService).findFeedbackLogById(1L);
        verify(feedbackRepository).findById(feedbackEntity.getId());
        verify(feedbackRepository).save(feedbackEntity);
        verify(changeLogService).updateFeedbackLog(feedbackChangeLogEntity);

        assertEquals(feedbackLogDTO.getId(), result.getId());

        assertEquals(feedbackLogDTO.getFirstName(), result.getFirstName());
        assertEquals(feedbackLogDTO.getOwnerEmail(), result.getOwnerEmail());
        assertEquals(feedbackLogDTO.getSubmitDate(), result.getSubmitDate());

        assertEquals(FeedbackStatusEnum.RESOLVED, feedbackEntity.getStatus());
        assertEquals(TypeChangeEnum.STATUS, feedbackChangeLogEntity.getTypeChange());
        assertEquals(FeedbackStatusEnum.NEW.toString(), feedbackChangeLogEntity.getOldValue());
        assertEquals(FeedbackStatusEnum.RESOLVED.toString(), feedbackChangeLogEntity.getNewValue());
    }

    @Test
    void resolveFeedback_ShouldThrowException_WhenFeedbackLogDoesNotExist() {
        when(changeLogService.findFeedbackLogById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            feedbackService.resolveFeedback("1");
        });

        assertEquals("Feedback is corrupt!", exception.getMessage());

        verify(changeLogService).findFeedbackLogById(1L);
        verify(feedbackRepository, never()).findById(anyLong());
        verify(feedbackRepository, never()).save(any(FeedbackEntity.class));
        verify(changeLogService, never()).updateFeedbackLog(any(FeedbackChangeLogEntity.class));
        verify(modelMapper, never()).map(any(FeedbackChangeLogEntity.class), eq(FeedbackLogDTO.class));
    }

    @Test
    void resolveFeedback_ShouldThrowException_WhenFeedbackEntityDoesNotExist() {
        when(changeLogService.findFeedbackLogById(1L)).thenReturn(Optional.of(feedbackChangeLogEntity));
        when(feedbackRepository.findById(feedbackEntity.getId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            feedbackService.resolveFeedback("1");
        });

        assertEquals("Feedback is corrupt!", exception.getMessage());

        verify(changeLogService).findFeedbackLogById(1L);
        verify(feedbackRepository).findById(feedbackEntity.getId());
        verify(feedbackRepository, never()).save(any(FeedbackEntity.class));
        verify(changeLogService, never()).updateFeedbackLog(any(FeedbackChangeLogEntity.class));
        verify(modelMapper, never()).map(any(FeedbackChangeLogEntity.class), eq(FeedbackLogDTO.class));
    }

    @Test
    void removeFeedback_ShouldReturnFeedbackLogDTO_WhenFeedbackIsRemoved() {
        when(changeLogService.findFeedbackLogById(1L)).thenReturn(Optional.of(feedbackChangeLogEntity));
        when(feedbackRepository.findById(feedbackEntity.getId())).thenReturn(Optional.of(feedbackEntity));

        FeedbackLogDTO result = feedbackService.removeFeedback("1");

        assertNotNull(result);
        assertEquals(removedFeedbackLogDTO.getId(), result.getId());

        verify(feedbackRepository).saveAndFlush(feedbackEntity);
        verify(changeLogService).updateFeedbackLog(feedbackChangeLogEntity);

        assertEquals(FeedbackStatusEnum.REMOVED, feedbackEntity.getStatus());
        assertEquals(TypeChangeEnum.STATUS, feedbackChangeLogEntity.getTypeChange());
        assertEquals(FeedbackStatusEnum.NEW.toString(), feedbackChangeLogEntity.getOldValue());
        assertEquals(FeedbackStatusEnum.REMOVED.toString(), feedbackChangeLogEntity.getNewValue());
    }

    @Test
    void removeFeedback_ShouldThrowRuntimeException_WhenFeedbackLogDoesNotExist() {
        when(changeLogService.findFeedbackLogById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            feedbackService.removeFeedback("1");
        });

        assertEquals("Feedback is corrupt!", exception.getMessage());
    }

    @Test
    void removeFeedback_ShouldThrowRuntimeException_WhenFeedbackEntityDoesNotExist() {
        when(changeLogService.findFeedbackLogById(1L)).thenReturn(Optional.of(feedbackChangeLogEntity));
        when(feedbackRepository.findById(feedbackEntity.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            feedbackService.removeFeedback("1");
        });

        assertEquals("Feedback is corrupt!", exception.getMessage());
    }

}


