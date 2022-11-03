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
    @ManyToOne
    private Workspace workspace;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private WorkspaceRole extendsRole;
}
