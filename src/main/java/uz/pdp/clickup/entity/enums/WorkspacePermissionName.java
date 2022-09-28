package uz.pdp.clickup.entity.enums;

public enum WorkspacePermissionName {
    CAN_ADD_OR_REMOVE_MEMBER("ADD/REMOVE MEMBERS","Gives users permission to add or remove memebers"),
    CAN_MANAGE_STATUS("Manage status","Gives users permission to manage status");

    private String name;
    private String description;

    WorkspacePermissionName(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
