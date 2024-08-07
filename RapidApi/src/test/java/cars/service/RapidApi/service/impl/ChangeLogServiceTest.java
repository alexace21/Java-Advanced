package cars.service.RapidApi.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import cars.service.RapidApi.enums.CarStatusEnum;
import cars.service.RapidApi.enums.TypeChangeEnum;
import cars.service.RapidApi.model.dtos.CarLogDTO;
import cars.service.RapidApi.model.entities.CarChangeLogEntity;
import cars.service.RapidApi.model.entities.CarEntity;
import cars.service.RapidApi.repository.CarChangeLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class ChangeLogServiceTest {

    @Mock
    private CarChangeLogRepository carChangeLogRepository;

    @InjectMocks
    private ChangeLogServiceImpl changeLogService;

    private CarEntity carEntity;
    private CarChangeLogEntity carChangeLogEntity;

    @BeforeEach
    void setUp() {
        carEntity = new CarEntity();

        carEntity.setId(1L);
        carEntity.setMake("Ford");
        carEntity.setModel("Mustang");
        carEntity.setOwner("John Doe");
        carEntity.setPrice("30000");


        carChangeLogEntity = new CarChangeLogEntity(
                TypeChangeEnum.CREATION,
                "Ford",
                "Mustang",
                "John Doe",
                1L,
                LocalDate.now(),
                null,
                CarStatusEnum.AVAILABLE.toString(),
                "30000"
        );

        carChangeLogEntity.setId(1L);
    }

    @Test
    void createCarOfferChangeLog_ShouldPersistChangeLog() {
        changeLogService.createCarOfferChangeLog(carEntity);

        verify(carChangeLogRepository).save(any(CarChangeLogEntity.class));
    }

    @Test
    void getAllCarLogs_ShouldReturnListOfCarLogs() {
        when(carChangeLogRepository.findAll()).thenReturn(List.of(carChangeLogEntity));
        List<CarLogDTO> result = changeLogService.getAllCarLogs();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Ford", result.get(0).getMake());

        verify(carChangeLogRepository).findAll();
    }

    @Test
    void trackRemovalOfCar_ShouldPersistChangeLogWithStatusChange() {
        changeLogService.trackRemovalOfCar(carEntity);

        verify(carChangeLogRepository).save(argThat(log ->
                log.getTypeChange() == TypeChangeEnum.STATUS &&
                        log.getOldValue().equals(CarStatusEnum.AVAILABLE.toString()) &&
                        log.getNewValue().equals(CarStatusEnum.SOLD.toString())
        ));
    }
}

