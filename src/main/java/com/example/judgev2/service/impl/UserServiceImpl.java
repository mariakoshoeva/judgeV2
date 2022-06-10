package com.example.judgev2.service.impl;

import com.example.judgev2.models.binding.UserLoginBindingModel;
import com.example.judgev2.models.binding.UserRegisterBindingModel;
import com.example.judgev2.models.entity.RoleEntity;
import com.example.judgev2.models.entity.UserEntity;
import com.example.judgev2.models.enums.RoleNameEnum;
import com.example.judgev2.repositoty.UserRepository;
import com.example.judgev2.security.CurrentUser;
import com.example.judgev2.service.RoleService;
import com.example.judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(CurrentUser currentUser, PasswordEncoder passwordEncoder, RoleService roleService, UserRepository userRepository, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        UserEntity userEntity = modelMapper.map(userRegisterBindingModel, UserEntity.class);
        String encode = passwordEncoder.encode(userRegisterBindingModel.getPassword());
        RoleEntity role = roleService.findRole(RoleNameEnum.USER);
        userEntity.setPassword(encode).setRole(role);
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity find(UserLoginBindingModel userLoginBindingModel) {
        Optional<UserEntity> user = this.userRepository.findByUsername(userLoginBindingModel.getUsername());
        if(user.isPresent()){
           if(passwordEncoder.matches(userLoginBindingModel.getPassword(),user.get().getPassword())){
               return user.get();
           }
        }
        return null;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        UserEntity userEntity = this.find(userLoginBindingModel);
        if(userEntity == null){
            return false;
        }
        currentUser
                .setUsername(userEntity.getUsername())
                .setId(userEntity.getId())
                .setRole(userEntity.getRole().getName());
        return true;
    }

    @Override
    public void logout() {
        currentUser.setRole(null).setUsername(null).setId(null);
    }

    @Override
    public List<String> getAllUserNames() {
        return this.userRepository.findAllUsernames();
    }

    @Override
    public void changeRole(String username, String role) {
        Optional<UserEntity> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()){
            RoleEntity roleEntity = roleService.findRole(RoleNameEnum.valueOf(role.toUpperCase()));
            UserEntity userEntity = byUsername.get();
            if(userEntity.getRole()!= roleEntity){
                userEntity.setRole(roleEntity);
                userRepository.save(userEntity);
            }
        }
    }

    @Override
    public UserEntity findById(Long id) {
        return this.userRepository.findById(id).get();
    }


}
