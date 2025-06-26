package com.example.mywas.domain.order.dto;

import com.example.mywas.domain.order.Order;
import com.example.mywas.domain.order.OrderItem;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderCompleteList {
    private Order order; // 주문정보
    private OrderItem orderItem;  // 어떤 메뉴와 수량을 시켰는지
}
