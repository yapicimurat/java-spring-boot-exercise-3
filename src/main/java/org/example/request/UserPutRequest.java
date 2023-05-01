package org.example.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserPutRequest {
    @NotNull
    @NotBlank
    @Min(3)
    @Max(50)
    private String firstName;
    @NotNull
    @NotBlank
    @Min(3)
    @Max(50)
    private String lastName;
    @NotNull
    @NotBlank
    @Min(8)
    @Max(200)
    private String password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
