package com.example.mywas.domain.order;

import com.example.mywas.domain.order.dto.OrderMenu;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Builder
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
    private String deliveryPersonId;
    private int totalPrice;
    private String status;
    private String address;
    private OffsetDateTime orderedAt;
    private String uniqueStr;
}
