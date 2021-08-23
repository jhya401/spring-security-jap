package com.study.springsecurityjap.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;
    private String password;
    private boolean active;
    private String roles;

    public User() {}

    @Builder
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.active = true;
        this.roles = "ROLE_USER";
    }
}
