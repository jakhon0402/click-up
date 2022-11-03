package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickup.entity.WorkspacePermission;

import java.util.UUID;

public interface WorkspacePermissionRepo extends JpaRepository<WorkspacePermission, UUID> {
}
