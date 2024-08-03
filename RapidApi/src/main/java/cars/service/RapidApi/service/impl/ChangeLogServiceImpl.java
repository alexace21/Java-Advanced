package cars.service.RapidApi.service.impl;

import cars.service.RapidApi.model.dtos.CarLogDTO;
import cars.service.RapidApi.model.entities.CarChangeLogEntity;
import cars.service.RapidApi.model.entities.CarEntity;
import cars.service.RapidApi.repository.CarChangeLogRepository;
import cars.service.RapidApi.service.ChangeLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ChangeLogServiceImpl implements ChangeLogService {

    private final CarChangeLogRepository carChangeLogRepository;

    public ChangeLogServiceImpl(CarChangeLogRepository carChangeLogRepository) {
        this.carChangeLogRepository = carChangeLogRepository;
    }

    @Override
    public void createCarOfferChangeLog(CarEntity savedEntity) {
        CarChangeLogEntity carChangeLogEntity = new CarChangeLogEntity(
                savedEntity.getMake(),
                savedEntity.getModel(),
                savedEntity.getOwner(),
                savedEntity,
                LocalDate.now()
        );

        this.carChangeLogRepository.save(carChangeLogEntity);
    }

    @Override
    public List<CarLogDTO> getAllCarLogs() {
        List<CarChangeLogEntity> logEntities = this.carChangeLogRepository.findAll();

        System.out.println();
        return null;
    }
}
