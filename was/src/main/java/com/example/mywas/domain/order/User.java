package com.example.mywas.domain.order;

import lombok.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 사용자
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role; // customer, owner, admin

    public User(String username, String email, String passowerd, String role){
        this.username = username;
        this.email = email;
        this.password = passowerd;
        this.role = role;
    }

}
