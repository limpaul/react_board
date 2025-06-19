package com.example.mywas.domain.order;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Menu {
    private Long id;
    private String name;
    private int price;
    private int category;
    private String description;
    private String image;
    private Float score;
    private LocalDateTime createdAt;
    private Long restaurantId;
    Restaurant restaurant; // 어느 식당 소속
}
