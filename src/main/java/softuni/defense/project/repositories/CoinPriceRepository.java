package softuni.defense.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.defense.project.model.entities.CoinPriceEntity;

import java.util.Optional;

@Repository
public interface CoinPriceRepository extends JpaRepository<CoinPriceEntity, Long> {
    Optional<CoinPriceEntity> findByName(String ripple);
}
