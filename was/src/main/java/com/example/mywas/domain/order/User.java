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
    private String address;
    private String role; // customer, owner, admin
}
