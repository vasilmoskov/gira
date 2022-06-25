package bg.softuni.gira.service.impl;

import bg.softuni.gira.models.entities.UserEntity;
import bg.softuni.gira.models.dto.TaskDto;
import bg.softuni.gira.models.entities.ClassificationEntity;
import bg.softuni.gira.models.entities.TaskEntity;
import bg.softuni.gira.models.enums.ProgressEnum;
import bg.softuni.gira.repositories.ClassificationRepository;
import bg.softuni.gira.repositories.TaskRepository;
import bg.softuni.gira.repositories.UserRepository;
import bg.softuni.gira.service.TaskService;
import bg.softuni.gira.utils.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ClassificationRepository categoryRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository shipRepository, UserRepository userRepository, CurrentUser currentUser, ClassificationRepository categoryRepository,
                           ModelMapper modelMapper) {
        this.taskRepository = shipRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(TaskDto taskDto) {
        ClassificationEntity categoryEntity = categoryRepository.findByName(taskDto.getClassification()).orElseThrow();
        UserEntity userEntity = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow();

        TaskEntity taskEntity = this.modelMapper.map(taskDto, TaskEntity.class)
                .setClassification(categoryEntity)
                .setProgress(ProgressEnum.OPEN)
                .setUser(userEntity);

        this.taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public void progress(long id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow();

        switch (taskEntity.getProgress()) {
            case OPEN -> {
                taskEntity.setProgress(ProgressEnum.IN_PROGRESS);
                taskRepository.save(taskEntity);
            }
            case IN_PROGRESS -> {
                taskEntity.setProgress(ProgressEnum.COMPLETED);
                taskRepository.save(taskEntity);
            }
            case COMPLETED -> taskRepository.delete(taskEntity);
        }
    }

    private TaskDto toDto(TaskEntity entity) {
        return this.modelMapper.map(entity, TaskDto.class)
                .setClassification(entity.getClassification().getName())
                .setUser(entity.getUser().getUsername());
    }
}
