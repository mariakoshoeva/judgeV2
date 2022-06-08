package com.example.judgev2.service.impl;

import com.example.judgev2.models.binding.UserRegisterBindingModel;
import com.example.judgev2.models.entity.RoleEntity;
import com.example.judgev2.models.entity.UserEntity;
import com.example.judgev2.models.enums.RoleNameEnum;
import com.example.judgev2.repositoty.UserRepository;
import com.example.judgev2.service.RoleService;
import com.example.judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(PasswordEncoder passwordEncoder, RoleService roleService, UserRepository userRepository, ModelMapper modelMapper) {
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
}
