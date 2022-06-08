package com.example.judgev2.service.impl;

import com.example.judgev2.models.entity.RoleEntity;
import com.example.judgev2.models.enums.RoleNameEnum;
import com.example.judgev2.repositoty.RoleRepository;
import com.example.judgev2.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if(roleRepository.count() == 0){
            roleRepository.save(new RoleEntity().setName(RoleNameEnum.USER));
            roleRepository.save(new RoleEntity().setName(RoleNameEnum.ADMIN));
        }
    }

    @Override
    public RoleEntity findRole(RoleNameEnum name) {
        return this.roleRepository.findByName(name).orElse(null);
    }
}
