package com.example.mywas.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItem {
    private Long id;
    private Order order;
    private Menu menu;
    private int quantity;
    private int priceAtOrder;
}
