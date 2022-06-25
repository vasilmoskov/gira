package bg.softuni.gira.repositories;

import bg.softuni.gira.models.entities.ClassificationEntity;
import bg.softuni.gira.models.entities.TaskEntity;
import bg.softuni.gira.models.enums.ProgressEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findByName(String name);
}
