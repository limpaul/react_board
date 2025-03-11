package com.example.mywas.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CartItem {
    private Long id;
    private Cart cart;
    private Menu menu;
    int quantity;
}
