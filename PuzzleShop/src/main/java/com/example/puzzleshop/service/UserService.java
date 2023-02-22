package com.example.puzzleshop.service;

import com.example.puzzleshop.model.entity.User;
import com.example.puzzleshop.model.service.UserServiceModel;

public interface UserService {
    void register(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void login(String id, String username);

    User findById(String id);
}
