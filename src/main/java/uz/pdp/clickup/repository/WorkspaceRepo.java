package uz.pdp.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickup.entity.Workspace;

import java.util.UUID;

public interface WorkspaceRepo extends JpaRepository<Workspace,Long> {
    boolean existsByOwnerIdAndName(UUID id,String name);
}
