package softuni.defense.project.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;
import softuni.defense.project.model.dtos.FeedbackLogDTO;
import softuni.defense.project.model.dtos.UserLogDTO;
import softuni.defense.project.model.entities.*;
import softuni.defense.project.model.enums.FeedbackRatingEnum;
import softuni.defense.project.model.enums.FeedbackStatusEnum;
import softuni.defense.project.model.enums.TypeChangeEnum;
import softuni.defense.project.model.enums.UserRoleEnum;
import softuni.defense.project.repositories.FeedbackChangeLogRepository;
import softuni.defense.project.repositories.UserChangeLogRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ChangeLogServiceImplTest {
    @Mock
    private FeedbackChangeLogRepository feedbackChangeLogRepository;

    @Mock
    private UserChangeLogRepository userChangeLogRepository;

    @Mock
    private RestClient carRestClient;  // Depending on its usage you might not need to mock this for createUserChangeLog

    @InjectMocks
    private ChangeLogServiceImpl changeLogService;

    private UserEntity savedUser;
    private FeedbackChangeLogEntity feedbackEntity;
    private UserChangeLogEntity userEntity;
    private FeedbackChangeLogEntity feedbackLog;
    @BeforeEach
    void setUp() {
        // Assuming UserEntity and related classes are setup correctly
        savedUser = new UserEntity();
        savedUser.setEmail("test@example.com");
        savedUser.setFirstName("John");
        savedUser.setLastName("Doe");
        savedUser.setRoles(List.of(new UserRoleEntity(UserRoleEnum.ADMIN)));  // Make sure UserRoleEntity is properly instantiated


        // Setup for FeedbackChangeLogEntity
        feedbackEntity = new FeedbackChangeLogEntity();
        feedbackEntity.setId(1L);
        feedbackEntity.setNewValue(FeedbackStatusEnum.NEW.toString());
        feedbackEntity.setOwner(savedUser);
        feedbackEntity.setSatisfaction(FeedbackRatingEnum.THE_BEST);
        feedbackEntity.setRecommendation("Highly recommend");
        feedbackEntity.setSubmitDate(LocalDate.now());

        // Setup for UserChangeLogEntity
        userEntity = new UserChangeLogEntity();
        userEntity.setEmail("test@example.com");
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setRegisteredDate(LocalDate.now());
        userEntity.setRole("ADMIN");

        feedbackLog = new FeedbackChangeLogEntity();
        feedbackLog.setId(1L);
        feedbackLog.setNewValue(FeedbackStatusEnum.NEW.toString());
    }

    @Test
    void createUserChangeLog_ShouldCreateLogSuccessfully() {
        // No need to do anything specific before calling the method if you're just testing if it's called correctly

        changeLogService.createUserChangeLog(savedUser);

        // Verify that save is called on the repository with any UserChangeLogEntity object
        verify(userChangeLogRepository).save(any(UserChangeLogEntity.class));

        // Optionally you could capture the argument passed to save and make assertions on it
        ArgumentCaptor<UserChangeLogEntity> captor = ArgumentCaptor.forClass(UserChangeLogEntity.class);
        verify(userChangeLogRepository).save(captor.capture());
        UserChangeLogEntity capturedValue = captor.getValue();

        assertEquals("test@example.com", capturedValue.getEmail());
        assertEquals("John", capturedValue.getFirstName());
        assertEquals("Doe", capturedValue.getLastName());
        assertEquals(LocalDate.now(), capturedValue.getRegisteredDate());
        assertEquals(TypeChangeEnum.CREATION, capturedValue.getTypeChange());
        assertEquals("NEW", capturedValue.getNewValue());
        // Ensure the Role matches what was set, assuming getRoles().get(0).getRole() does not throw IndexOutOfBoundsException
        assertEquals("ADMIN", capturedValue.getRole());
    }

    @Test
    void createFeedbackChangeLog_ShouldCorrectlyMapSatisfaction() {
        FeedbackEntity savedFeedback = new FeedbackEntity();
        savedFeedback.setSelectedOption("satisfied");
        savedFeedback.setOwnerUser(savedUser);
        savedFeedback.setRecommendOption("Definitely");

        changeLogService.createFeedbackChangeLog(savedFeedback);

        ArgumentCaptor<FeedbackChangeLogEntity> captor = ArgumentCaptor.forClass(FeedbackChangeLogEntity.class);
        verify(feedbackChangeLogRepository).save(captor.capture());
        FeedbackChangeLogEntity capturedLog = captor.getValue();

        assertEquals(FeedbackRatingEnum.SATISFIED, capturedLog.getSatisfaction());
        assertEquals("test@example.com", capturedLog.getOwner().getEmail());
        assertEquals("Definitely", capturedLog.getRecommendation());
        assertEquals(LocalDate.now(), capturedLog.getSubmitDate());
        assertEquals(FeedbackStatusEnum.NEW.toString(), capturedLog.getNewValue());
    }

    @Test
    void createFeedbackChangeLog_ShouldHandleAllSatisfactionOptions() {
        // Use a parameterized test or multiple tests to cover all cases
        String[] options = {"very_satisfied", "satisfied", "neutral", "dissatisfied", "very_dissatisfied"};
        FeedbackRatingEnum[] expectedRatings = {
                FeedbackRatingEnum.THE_BEST, FeedbackRatingEnum.SATISFIED, FeedbackRatingEnum.OKAY,
                FeedbackRatingEnum.NOT_HAPPY, FeedbackRatingEnum.DISAPPOINTING
        };

        for (int i = 0; i < options.length; i++) {
            FeedbackEntity savedFeedback = new FeedbackEntity();
            savedFeedback.setSelectedOption(options[i]);
            savedFeedback.setOwnerUser(savedUser);
            savedFeedback.setRecommendOption("Definitely");

            changeLogService.createFeedbackChangeLog(savedFeedback);

            ArgumentCaptor<FeedbackChangeLogEntity> captor = ArgumentCaptor.forClass(FeedbackChangeLogEntity.class);
            verify(feedbackChangeLogRepository, times(i + 1)).save(captor.capture());
            FeedbackChangeLogEntity capturedLog = captor.getValue();

            assertEquals(expectedRatings[i], capturedLog.getSatisfaction(), "Failed for option: " + options[i]);
            assertEquals("test@example.com", capturedLog.getOwner().getEmail());
            assertEquals("Definitely", capturedLog.getRecommendation());
            assertEquals(LocalDate.now(), capturedLog.getSubmitDate());
            assertEquals(FeedbackStatusEnum.NEW.toString(), capturedLog.getNewValue());
        }
    }

    @Test
    void getAllFeedbackLogs_ShouldReturnFilteredLogs() {
        List<FeedbackChangeLogEntity> logs = new ArrayList<>();
        logs.add(feedbackEntity);  // This should be included
        FeedbackChangeLogEntity oldLog = new FeedbackChangeLogEntity();
        oldLog.setNewValue(FeedbackStatusEnum.RESOLVED.toString());  // This should not be included
        logs.add(oldLog);

        when(feedbackChangeLogRepository.findAll()).thenReturn(logs);

        List<FeedbackLogDTO> result = changeLogService.getAllFeedbackLogs();

        assertNotNull(result);
        assertEquals(1, result.size());  // Only one log meets the criteria
        assertEquals(feedbackEntity.getOwner().getFirstName(), result.get(0).getFirstName());
    }

    @Test
    void getAllRegisteredUsers_ShouldReturnAllUserLogs() {
        List<UserChangeLogEntity> logs = List.of(userEntity);
        when(userChangeLogRepository.findAll()).thenReturn(logs);

        List<UserLogDTO> result = changeLogService.getAllRegisteredUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("test@example.com", result.get(0).getEmail());
    }

    @Test
    void findFeedbackLogById_ShouldReturnLogWhenPresent() {
        when(feedbackChangeLogRepository.findById(1L)).thenReturn(Optional.of(feedbackLog));

        Optional<FeedbackChangeLogEntity> result = changeLogService.findFeedbackLogById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void findFeedbackLogById_ShouldReturnEmptyWhenNotPresent() {
        when(feedbackChangeLogRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<FeedbackChangeLogEntity> result = changeLogService.findFeedbackLogById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void updateFeedbackLog_ShouldInvokeSaveOnRepository() {
        changeLogService.updateFeedbackLog(feedbackLog);

        verify(feedbackChangeLogRepository).save(feedbackLog);
    }

}
