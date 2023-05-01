package org.example.model;

public enum RoleType {
    ADMIN("ADMIN"),
    NORMAL("NORMAL");
    private String roleName;
    RoleType(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleName() {
        return this.roleName;
    }
}
