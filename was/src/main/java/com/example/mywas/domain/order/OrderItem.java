package com.example.mywas.domain.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderItem { // 사용자가 주문한 목록에 대한 메뉴들
    private Long id;
    private Order order;
    private Menu menu;
    private int quantity;
    private int priceAtOrder;
}
