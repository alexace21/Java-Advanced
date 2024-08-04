package cars.service.RapidApi.service;

import cars.service.RapidApi.model.dtos.CarLogDTO;
import cars.service.RapidApi.model.entities.CarEntity;

import java.util.List;

public interface ChangeLogService {
    void createCarOfferChangeLog(CarEntity savedEntity);

    List<CarLogDTO> getAllCarLogs();

    void trackRemovalOfCar(CarEntity targetEntity);
}
