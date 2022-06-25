package bg.softuni.gira.repositories;

import bg.softuni.gira.models.entities.ClassificationEntity;
import bg.softuni.gira.models.enums.ClassificationEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassificationRepository extends JpaRepository<ClassificationEntity, Long> {
    Optional<ClassificationEntity> findByName(ClassificationEnum name);
}
