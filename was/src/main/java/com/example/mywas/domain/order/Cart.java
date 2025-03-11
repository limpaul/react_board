package com.example.mywas.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Cart {
    private Long id;
    private User user;
    private List<CartItem> items;
}
