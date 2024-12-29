package com.cinejava.services.interfaces;

import com.cinejava.models.User;

public interface IUsersService extends IGenericService<User> {
    boolean login(String userName, String password);

    String register(String userName, String firstName, String lastName, String password);
}
