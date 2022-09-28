package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.entity.enums.DependencyTypes;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskDependency extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Task task;

    @ManyToOne
    private TaskDependency taskDependency;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DependencyTypes dependencyType;
}
