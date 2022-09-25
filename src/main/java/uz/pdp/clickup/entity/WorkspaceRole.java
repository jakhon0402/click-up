package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.entity.enums.Colors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspaceRole extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @Column(nullable = false)
    private Workspace workspace;

    @ManyToOne
    private WorkspaceRole extendsRole;
}
