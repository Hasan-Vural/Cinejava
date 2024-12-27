package com.cinejava.services;

import com.cinejava.constants.DataStoreConstants;
import com.cinejava.models.User;

public class UsersService extends GenericService<User> {
    public UsersService() {
        super(User.class, DataStoreConstants.USER_STORE_NAME);
    }
}