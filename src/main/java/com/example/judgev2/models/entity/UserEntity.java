package com.example.judgev2.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity  extends BaseEntity{
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String git;
    @ManyToOne(fetch = FetchType.EAGER)
    private RoleEntity role;

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGit() {
        return git;
    }

    public UserEntity setGit(String git) {
        this.git = git;
        return this;
    }

    public RoleEntity getRole() {
        return role;
    }

    public UserEntity setRole(RoleEntity role) {
        this.role = role;
        return this;
    }
}
