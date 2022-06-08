package com.example.judgev2.models.entity;

import com.example.judgev2.models.enums.RoleNameEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private RoleNameEnum name;

    public RoleNameEnum getName() {
        return name;
    }

    public RoleEntity setName(RoleNameEnum name) {
        this.name = name;
        return this;
    }
}
