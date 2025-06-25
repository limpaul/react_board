package com.example.mywas.domain.order.dto;

import com.example.mywas.domain.order.Menu;
import com.example.mywas.domain.order.OrderItem;
import com.example.mywas.domain.order.Restaurant;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderMenu {
    private Long id;
    private String name;
    private int price;
    private int category;
    private String description;
    private String image;
    private Float score;
    private LocalDateTime createdAt;
    private Long restaurantId;
    private Menu menu;
    private int count; // 주문 수량
    Restaurant restaurant; // 어느 식당 소속
}

