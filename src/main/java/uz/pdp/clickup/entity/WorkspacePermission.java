package uz.pdp.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.entity.enums.Colors;
import uz.pdp.clickup.entity.enums.Permissions;
import uz.pdp.clickup.entity.enums.WorkspacePermissionName;

import javax.persistence.*;
import java.security.Permission;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspacePermission extends AbstractEntity{

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private WorkspaceRole workspaceRole;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<WorkspacePermissionName> permissionList;

}
