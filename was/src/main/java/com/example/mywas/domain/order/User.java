package com.example.mywas.domain.order;

import lombok.*;

// 사용자
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role; // customer, owner, admin
}
