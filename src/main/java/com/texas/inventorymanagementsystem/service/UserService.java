package com.texas.inventorymanagementsystem.service;

import com.texas.inventorymanagementsystem.model.User;

public interface UserService {

    User registerUser(User user);
    User loginUser(String username, String password);
}
