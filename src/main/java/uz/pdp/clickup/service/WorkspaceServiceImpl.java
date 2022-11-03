package uz.pdp.clickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import uz.pdp.clickup.entity.*;
import uz.pdp.clickup.entity.enums.AddType;
import uz.pdp.clickup.entity.enums.WorkspaceRoleName;
import uz.pdp.clickup.entity.enums.WorkspacePermissionName;
import uz.pdp.clickup.payload.ApiResponse;
import uz.pdp.clickup.payload.MemberDTO;
import uz.pdp.clickup.payload.WorkspaceDto;
import uz.pdp.clickup.repository.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkspaceServiceImpl implements WorkspaceService{

    @Autowired
    WorkspaceRepo workspaceRepo;
    @Autowired
    AttachmentRepo attachmentRepo;
    @Autowired
    WorkspaceUserRepo workspaceUserRepo;
    @Autowired
    WorkspaceRoleRepo workspaceRoleRepo;
    @Autowired
    WorkspacePermissionRepo workspacePermissionRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public ApiResponse addWorkspace(WorkspaceDto workspaceDto, User user) {
        if(workspaceRepo.existsByOwnerIdAndName(user.getId(),workspaceDto.getName())){
            return new ApiResponse("Bunday nomlik wprkspace mavjud !",false);
        }
        Workspace workspace = new Workspace(
                workspaceDto.getName(),
                workspaceDto.getColor(),
                user,
                workspaceDto.getAvatarId()==null?null:attachmentRepo.findById(workspaceDto.getAvatarId()).orElseThrow(()-> new RuntimeException())
        );

        workspaceRepo.save(workspace);
        WorkspaceRole ownerRole = workspaceRoleRepo.save(new WorkspaceRole(
                workspace,

                WorkspaceRoleName.ROLE_OWNER.name(),
                null
        ));
        WorkspaceRole adminRole = workspaceRoleRepo.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_ADMIN.name(), null));
        WorkspaceRole memberRole = workspaceRoleRepo.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_MEMBER.name(), null));
        WorkspaceRole guestRole = workspaceRoleRepo.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_GUEST.name(), null));


        //OWERGA HUQUQLARNI BERYAPAMIZ
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissions = new ArrayList<>();

        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    ownerRole,
                    workspacePermissionName);
            workspacePermissions.add(workspacePermission);
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissions.add(new WorkspacePermission(
                        adminRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissions.add(new WorkspacePermission(
                        memberRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissions.add(new WorkspacePermission(
                        guestRole,
                        workspacePermissionName));
            }

        }
        workspacePermissionRepo.saveAll(workspacePermissions);

        //WORKSPACE USER OCHDIK
        workspaceUserRepo.save(new WorkspaceUser(
                workspace,
                user,
                ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())

        ));


        return new ApiResponse("Workspace saqlandi !",true);
    }

    @Override
    public ApiResponse editWorkspace(WorkspaceDto workspaceDto, long id) {
        Optional<Workspace> optionalWorkspace = workspaceRepo.findById(id);
        if(optionalWorkspace.isEmpty()){
            return new ApiResponse("Bunday idlik workspace mavjud emas!", false);
        }
        Workspace workspace = optionalWorkspace.get();
        workspace.setName(workspaceDto.getName());
        workspace.setColor(workspaceDto.getColor());
        workspace.setAvatarId(workspaceDto.getAvatarId()==null?null:attachmentRepo.findById(workspaceDto.getAvatarId()).orElseThrow(()-> new RuntimeException()));
        return new ApiResponse("Workspace edit qilindi!",true);
    }

    @Override
    public ApiResponse deleteWorkspace(long id) {
        Optional<Workspace> optionalWorkspace = workspaceRepo.findById(id);
        if(optionalWorkspace.isEmpty()){
            return new ApiResponse("Bunday idlik workspace mavjud emas!",false);
        }
        workspaceRepo.delete(optionalWorkspace.get());
        return new ApiResponse("Workspace o'chirildi!",true);
    }

    @Override
    public ApiResponse changeOwnerWorkspace(long id, UUID ownerId) {
        Optional<Workspace> optionalWorkspace = workspaceRepo.findById(id);
        if(optionalWorkspace.isEmpty()){
            return new ApiResponse("Bunday idlik workspace mavjud emas!",false);
        }
        Optional<User> optionalUser = userRepo.findById(ownerId);
        if(optionalUser.isEmpty()){
            return new ApiResponse("Bunday idlik user mavjud emas!",false);
        }
        Workspace workspace = optionalWorkspace.get();
        workspace.setOwner(optionalUser.get());
        return new ApiResponse("Workspace owneri o'zgartirildi!",true);
    }

    @Override
    public ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO) throws ChangeSetPersister.NotFoundException {
        if (memberDTO.getAddType().equals(AddType.ADD)) {
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    workspaceRepo.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException()),
                    userRepo.findById(memberDTO.getId()).orElseThrow(() ->new ChangeSetPersister.NotFoundException()),
                    workspaceRoleRepo.findById(memberDTO.getRoleId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException()),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            workspaceUserRepo.save(workspaceUser);

            //TODO EMAILGA INVITE XABAR YUBORISH
        } else if (memberDTO.getAddType().equals(AddType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepo.findByWorkspaceIdAndUserId(id, memberDTO.getId()).orElseGet(WorkspaceUser::new);
            workspaceUser.setWorkspaceRole(workspaceRoleRepo.findById(memberDTO.getRoleId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException()));
            workspaceUserRepo.save(workspaceUser);
        } else if (memberDTO.getAddType().equals(AddType.REMOVE)) {
            workspaceUserRepo.deleteByWorkspaceIdAndUserId(id, memberDTO.getId());
        }
        return new ApiResponse("Muvaffaqiyatli", true);
    }

    @Override
    public ApiResponse joinToWorkspace(Long id, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepo.findByWorkspaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isPresent()) {
            WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepo.save(workspaceUser);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Error", false);
    }

}
