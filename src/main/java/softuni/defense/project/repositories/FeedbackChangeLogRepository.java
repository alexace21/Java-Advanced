package softuni.defense.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.defense.project.model.entities.FeedbackChangeLogEntity;
@Repository
public interface FeedbackChangeLogRepository extends JpaRepository<FeedbackChangeLogEntity, Long> {
}
