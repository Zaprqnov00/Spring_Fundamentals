package com.example.puzzleshop.service.impl;

import com.example.puzzleshop.model.service.UserServiceModel;
import com.example.puzzleshop.model.entity.User;
import com.example.puzzleshop.repository.UserRepository;
import com.example.puzzleshop.security.CurrentUser;
import com.example.puzzleshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);

        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return this.userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void login(String id, String username) {
        this.currentUser.setId(id);
        this.currentUser.setUsername(username);
    }

    @Override
    public User findById(String id) {

        return this.userRepository.findById(id).orElse(null);
    }
}
