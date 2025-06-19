package com.example.mywas.domain.order;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Menu {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String image;
    private String score;
    private LocalDateTime createdAt;
    private Long restaurantId;
    Restaurant restaurant; // 어느 식당 소속
}
