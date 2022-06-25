package bg.softuni.gira.models.entities;

import bg.softuni.gira.models.enums.ClassificationEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "classifications")
public class ClassificationEntity extends BaseEntity {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private ClassificationEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public ClassificationEnum getName() {
        return name;
    }

    public ClassificationEntity setName(ClassificationEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClassificationEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
