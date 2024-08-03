package cars.service.RapidApi.service;

import cars.service.RapidApi.model.entities.CarEntity;

public interface ChangeLogService {
    void createCarOfferChangeLog(CarEntity savedEntity);
}
