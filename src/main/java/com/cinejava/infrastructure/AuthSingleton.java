package com.cinejava.infrastructure;

import com.cinejava.models.User;

public class AuthSingleton {
    private static AuthSingleton instance;

    private User loggedInUser;

    private AuthSingleton() {}

    public static AuthSingleton getInstance() {
        if (instance == null) {
            instance = new AuthSingleton();
        }
        return instance;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void clearSession() {
        this.loggedInUser = null;
    }
}

