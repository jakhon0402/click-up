package uz.pdp.clickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickup.entity.User;
import uz.pdp.clickup.payload.ApiResponse;
import uz.pdp.clickup.payload.WorkspaceDto;
import uz.pdp.clickup.security.CurrentUser;
import uz.pdp.clickup.service.WorkspaceService;

import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    @Autowired
    WorkspaceService workspaceService;

    @PostMapping
    public HttpEntity<?> addWorkspace(@RequestBody WorkspaceDto workspaceDto, @CurrentUser User user){
        ApiResponse apiResponse = workspaceService.addWorkspace(workspaceDto, user);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editWorkspace(@PathVariable long id,@RequestBody WorkspaceDto workspaceDto){
        ApiResponse apiResponse = workspaceService.editWorkspace(workspaceDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWorkspace(@PathVariable long id){
        ApiResponse apiResponse = workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @PutMapping("/{id}/{ownerId}")
    public HttpEntity<?> changeOwner(@PathVariable long id, @PathVariable UUID ownerId){
        ApiResponse apiResponse = workspaceService.changeOwnerWorkspace(id, ownerId);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }
}
