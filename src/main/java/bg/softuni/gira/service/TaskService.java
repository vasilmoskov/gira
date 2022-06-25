package bg.softuni.gira.service;

import bg.softuni.gira.models.dto.TaskDto;

import java.math.BigDecimal;
import java.util.List;

public interface TaskService {

    void add(TaskDto shipDto);

    List<TaskDto> getAllTasks();

    void progress(long id);
}
