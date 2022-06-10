package com.example.judgev2.service;

import com.example.judgev2.models.binding.UserLoginBindingModel;
import com.example.judgev2.models.binding.UserRegisterBindingModel;
import com.example.judgev2.models.entity.UserEntity;

import java.util.List;

public interface UserService {
    void register(UserRegisterBindingModel userRegisterBindingModel);

    UserEntity find(UserLoginBindingModel userLoginBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();

    List<String> getAllUserNames();

    void changeRole(String username, String role);

    UserEntity findById(Long id);
}
