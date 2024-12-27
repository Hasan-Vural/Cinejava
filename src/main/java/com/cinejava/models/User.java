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

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    
}
