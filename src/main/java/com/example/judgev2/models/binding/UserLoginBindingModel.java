package com.example.judgev2.models.binding;

import org.hibernate.validator.constraints.Length;

public class UserLoginBindingModel {
    @Length(min = 2,max = 20, message = "Username must be between 2 and 20 characters!")
    private String username;
    @Length(min = 3, max = 20, message = "Password must be betweeen 3 and 20 characters!")
    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
