package com.cinejava.services.interfaces;

public interface IUsersService {
    boolean login(String userName, String password);
    String register(String userName, String password);
}
