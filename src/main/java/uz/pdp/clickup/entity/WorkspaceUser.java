package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.entity.enums.Colors;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspaceUser extends AbstractEntity{
    @ManyToOne
    private Workspace workspace;

    @ManyToOne
    private User user;

    @ManyToOne
    private WorkspaceRole workspaceRole;

    @Column(nullable = false)
    private Date dateInvited;

    @Column(nullable = false)
    private Date dateJoined;
}
