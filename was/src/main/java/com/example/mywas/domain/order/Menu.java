package com.example.mywas.domain.order;

import lombok.*;

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
    Restaurant restaurant; // 어느 식당 소속
}
