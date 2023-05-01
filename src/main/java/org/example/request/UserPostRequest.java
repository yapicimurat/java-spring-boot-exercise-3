package org.example.request;

import org.example.model.RoleType;
import javax.validation.constraints.*;

public class UserPostRequest {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 75)
    private String username;
    @NotNull
    @NotBlank
    @Size(min = 8, max = 200)
    private String password;
    @NotNull
    private String roleName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleType(String roleName) {
        this.roleName = roleName;
    }
}
