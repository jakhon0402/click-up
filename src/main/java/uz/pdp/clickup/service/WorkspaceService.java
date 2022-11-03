package uz.pdp.clickup.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import uz.pdp.clickup.entity.User;
import uz.pdp.clickup.payload.ApiResponse;
import uz.pdp.clickup.payload.MemberDTO;
import uz.pdp.clickup.payload.WorkspaceDto;

import java.util.UUID;

public interface WorkspaceService {
    ApiResponse addWorkspace(WorkspaceDto workspaceDto, User user);
    ApiResponse editWorkspace(WorkspaceDto workspaceDto,long id);
    ApiResponse deleteWorkspace(long id);
    ApiResponse changeOwnerWorkspace(long id, UUID ownerId);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO) throws ChangeSetPersister.NotFoundException;

    ApiResponse joinToWorkspace(Long id, User user);
//    ApiResponse addOrEditOrRemoveWorkspace(long id);
}
