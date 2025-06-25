package com.example.mywas.domain.order;

import com.example.mywas.domain.order.dto.OrderMenu;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order { // 주문 테이블
    private Long id;
    private Long userId;
    private User user;

    private Restaurant restaurant;
    private List<OrderItem> items;
    private List<OrderMenu> menus; // 메뉴 수량을 표현하기 위함
    private int totalMount;
    private int orderCount;
    private String uniqueStr;
    private LocalDateTime localDateTime;
}
