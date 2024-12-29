package com.cinejava.services.implementations;

import java.util.Optional;

import com.cinejava.common.CryptographyHelper;
import com.cinejava.constants.DataStoreConstants;
import com.cinejava.enums.UserRole;
import com.cinejava.exceptions.RegisterException;
import com.cinejava.infrastructure.AuthSingleton;
import com.cinejava.models.User;
import com.cinejava.services.interfaces.IUsersService;

public class UsersService extends GenericService<User> implements IUsersService{
    public UsersService() {
        super(User.class, DataStoreConstants.USER_STORE_NAME);
    }

    @Override
    public boolean login(String userName, String password)
    {
        Optional<User> signingUser = findUserByCredentials(userName, password);

        if (!signingUser.isPresent()) {
            return false;
        }

        AuthSingleton.getInstance().setLoggedInUser(signingUser.get());
        return true;
    }

    @Override
    public String register(String userName, String password)
    {
        try {
            validateRegisterRequest(userName, password);
    
            User newUser = new User(userName, CryptographyHelper.encrypt(password), UserRole.USER);

            dataStoreContext.insert(newUser);

            AuthSingleton.getInstance().setLoggedInUser(newUser);

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


//  USer Kontrol et

//user update

// userdelete