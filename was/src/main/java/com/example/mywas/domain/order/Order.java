package com.example.mywas.domain.order;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private Long id;
    private Long userId;
    private User user;

    private Restaurant restaurant;
    private List<OrderItem> items;
    private List<Menu> menus;
    private int totalMount;
    private int orderCount;
    private LocalDateTime localDateTime;
}
