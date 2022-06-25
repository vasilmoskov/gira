package bg.softuni.gira.models.dto;

import bg.softuni.gira.models.enums.ClassificationEnum;
import bg.softuni.gira.models.enums.ProgressEnum;
import bg.softuni.gira.models.validation.UniqueTask;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskDto {
    private long id;

    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    @UniqueTask
    private String name;

    @Size(min = 5, message = "Description length must be more than 5 characters!")
    private String description;

    private ProgressEnum progress;

    @NotNull(message = "Due date cannot be null")
    @FutureOrPresent(message = "The date cannot be in the past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dueDate;

    @NotNull(message = "Classification cannot be null!")
    private ClassificationEnum classification;

    private String user;

    public String getName() {
        return name;
    }

    public TaskDto setName(String name) {
        this.name = name;
        return this;
    }

    public ClassificationEnum getClassification() {
        return classification;
    }

    public TaskDto setClassification(ClassificationEnum classification) {
        this.classification = classification;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskDto setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public long getId() {
        return id;
    }

    public TaskDto setId(long id) {
        this.id = id;
        return this;
    }

    public ProgressEnum getProgress() {
        return progress;
    }

    public TaskDto setProgress(ProgressEnum progress) {
        this.progress = progress;
        return this;
    }

    public String getUser() {
        return user;
    }

    public TaskDto setUser(String user) {
        this.user = user;
        return this;
    }
}
