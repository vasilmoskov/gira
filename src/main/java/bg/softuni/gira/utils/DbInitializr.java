package bg.softuni.gira.utils;

import bg.softuni.gira.models.entities.ClassificationEntity;
import bg.softuni.gira.models.enums.ClassificationEnum;
import bg.softuni.gira.repositories.ClassificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInitializr implements CommandLineRunner {
    private final ClassificationRepository categoryRepository;

    public DbInitializr(ClassificationRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0) {
            return;
        }

        ClassificationEntity bug = new ClassificationEntity()
                .setName(ClassificationEnum.BUG);

        ClassificationEntity feature = new ClassificationEntity()
                .setName(ClassificationEnum.FEATURE);

        ClassificationEntity support = new ClassificationEntity()
                .setName(ClassificationEnum.SUPPORT);

        ClassificationEntity other = new ClassificationEntity()
                .setName(ClassificationEnum.OTHER);

        this.categoryRepository.saveAll(List.of(bug, feature, support, other));
    }
}
