package com.cinejava.infrastructure;

import com.cinejava.models.User;

public class AuthSession {
    private static AuthSession instance;

    private User loggedInUser;

    private AuthSession() {}

    public static AuthSession getInstance() {
        if (instance == null) {
            instance = new AuthSession();
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

