package com.texas.inventorymanagementsystem.serviceimpl;

import com.texas.inventorymanagementsystem.model.User;
import com.texas.inventorymanagementsystem.repository.UserRepo;
import com.texas.inventorymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User registerUser(User user) {
        if(userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        return userRepo.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        return userRepo.findByUsername(username).filter(user -> user.getPassword().equals(password)).orElseThrow(() ->new RuntimeException("Invalid Credintials"));
    }
}
