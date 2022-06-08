package com.example.judgev2.service;

import com.example.judgev2.models.entity.RoleEntity;
import com.example.judgev2.models.enums.RoleNameEnum;

public interface RoleService {
    void initRoles();

    RoleEntity findRole(RoleNameEnum user);
}
