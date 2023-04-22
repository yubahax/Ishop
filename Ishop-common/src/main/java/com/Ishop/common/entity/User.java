package com.Ishop.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int uid;
    private String username;
    private String password;


    public User() {

    }

    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }
}
