package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickup.entity.WorkspaceRole;

import java.util.UUID;

public interface WorkspaceRoleRepo extends JpaRepository<WorkspaceRole, UUID> {
}
