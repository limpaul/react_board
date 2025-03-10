package com.example.mywas.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItem {
    private Long id;
    private Cart cart;
    private Menu menu;
    int quantity;
}
