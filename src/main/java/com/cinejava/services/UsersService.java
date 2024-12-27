package com.cinejava.services;

import java.util.Optional;

import com.cinejava.common.CryptographyHelper;
import com.cinejava.constants.DataStoreConstants;
import com.cinejava.enums.UserRole;
import com.cinejava.exceptions.RegisterException;
import com.cinejava.infrastructure.AuthSession;
import com.cinejava.models.User;

public class UsersService extends GenericService<User> {
    public UsersService() {
        super(User.class, DataStoreConstants.USER_STORE_NAME);
    }

    public boolean login(String userName, String password)
    {
        Optional<User> signingUser = findUserByCredentials(userName, password);

        if (!signingUser.isPresent()) {
            return false;
        }

        AuthSession.getInstance().setLoggedInUser(signingUser.get());
        return true;
    }

    public String register(String userName, String password)
    {
        try {
            validateRegisterRequest(userName, password);
    
            User newUser = new User(userName, CryptographyHelper.encrypt(password), UserRole.NormalUser);

            dataStoreContext.insert(newUser);

            AuthSession.getInstance().setLoggedInUser(newUser);

            return "Kayit basarili!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private Optional<User> findUserByCredentials(String userName, String password) {
        return dataStoreContext.getAll()
            .stream()
            .filter(user -> user.getUsername().equals(userName) &&
                            isPasswordValid(password, user.getPassword()))
            .findFirst();
    }
    
    private boolean isPasswordValid(String plainPassword, String encryptedPassword) {
        try {
            String decryptedPassword = CryptographyHelper.decrypt(encryptedPassword);
            return decryptedPassword.equals(plainPassword);      
        } catch (Exception e) {
            return false;
        }
    }

    private void validateRegisterRequest(String userName, String password) {
        if (dataStoreContext.getAll().stream().anyMatch(user -> user.getUsername().equals(userName))) {
            throw new RegisterException("Username already exist!");
        }

        if (userName == null || userName.trim().isEmpty()) {
            throw new RegisterException("Username cannot be empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new RegisterException("Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new RegisterException("Password must be at least 8 characters");
        }
    }
}