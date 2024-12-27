package com.cinejava.models;

import com.cinejava.common.BaseModel;
import com.cinejava.enums.UserRole;

public class User extends BaseModel {
    private String username;
    private String password;
    public UserRole role;

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters ve Setters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }
}
