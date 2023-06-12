package org.example.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@EntityListeners(EntityListener.class)
public class Role extends BaseModel {
    @Enumerated(EnumType.ORDINAL)
    private RoleType roleType = RoleType.NORMAL;
    public Role() {

    }
    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleType == role.roleType;
    }

    @Override
    public int hashCode() {
        return roleType.getRoleName().hashCode();
    }
}
