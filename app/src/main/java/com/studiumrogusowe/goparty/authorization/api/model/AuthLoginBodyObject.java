package com.studiumrogusowe.goparty.authorization.api.model;

/**
 * Created by O10 on 16.04.15.
 */
public class AuthLoginBodyObject {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
