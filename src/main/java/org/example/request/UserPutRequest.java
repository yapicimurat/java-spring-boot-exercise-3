package org.example.request;

import javax.validation.constraints.*;

public class UserPutRequest {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;
    @Size(min = 8, max = 200)
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
