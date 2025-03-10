package com.example.mywas.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Menu {
    private Long id;
    private String name;
    private int price;
    private String description;
    Restaurant restaurant; // 어느 식당 소속
}
