package com.example.mywas.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class Order {
    private Long id;
    private User user;
    private Restaurant restaurant;
    private List<OrderItem> items;
    String status;
    private LocalDateTime localDateTime;
}
