package bg.softuni.gira.models.validation;

import bg.softuni.gira.repositories.TaskRepository;
import bg.softuni.gira.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueTaskValidator implements ConstraintValidator<UniqueTask, String> {
    private final TaskRepository taskRepository;

    public UniqueTaskValidator(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.taskRepository.findByName(value).isEmpty();
    }
}
