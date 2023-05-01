package org.example.request;

import javax.validation.constraints.Size;

public class AuthenticationRequest {
    @Size(min = 3, max = 50)
    private String username;
    @Size(min = 8, max = 200)
    private String password;

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
}
