package com.example.mywas.domain.order;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String description;
    private Long rating;
    private int minOrderPrice;
    private int deliveryTimeMin;
    private int deliveryTimeMax;
    private int deliveryFee;
    private String image;
    private LocalDateTime createdAt;
    private Long userId;
    private User user; // 어느 식당 사장의 계정
}
