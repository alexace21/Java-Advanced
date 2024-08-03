package cars.service.RapidApi.repository;

import cars.service.RapidApi.model.entities.CarChangeLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarChangeLogRepository extends JpaRepository<CarChangeLogEntity, Long> {
}
