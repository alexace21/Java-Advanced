package softuni.defense.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.defense.project.model.entities.UserChangeLogEntity;
@Repository
public interface UserChangeLogRepository extends JpaRepository<UserChangeLogEntity, Long> {
}
