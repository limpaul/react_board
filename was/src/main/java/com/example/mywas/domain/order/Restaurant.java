package com.example.mywas.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private User user; // 어느 식당 사장의 계정
}
