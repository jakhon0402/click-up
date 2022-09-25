package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.entity.enums.Colors;
import uz.pdp.clickup.entity.enums.TaskPermissions;

import javax.persistence.*;
import java.security.Permission;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProjectUser extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Colors color;

    @ManyToOne
    @Column(nullable = false)
    private Project project;

    @ManyToOne
    @Column(nullable = false)
    private User user;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<TaskPermissions> taskPermissions;


}
