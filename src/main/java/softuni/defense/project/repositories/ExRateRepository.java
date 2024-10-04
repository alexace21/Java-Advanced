package softuni.defense.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.defense.project.model.entities.ExRateEntity;

import java.util.Optional;

@Repository
public interface ExRateRepository extends JpaRepository<ExRateEntity, Long> {
    Optional<ExRateEntity> findByCurrency(String currency);
}
