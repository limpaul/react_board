package com.example.mywas.repository.order;

import com.example.mywas.domain.order.Order;
import com.example.mywas.domain.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Value("${sql.restaurant.order.order}")
    private String createOrder;

    @Value("${sql.restaurant.order.item}")
    private String createItem;

    @Value("${sql.restaurant.order.findOrderIdByUniqueStr}")
    private String findOrderIdByUniqueStr;

    @Value("${sql.restaurant.item.findItemsByOrderId}")
    private String findItemsByOrderId;

    @Value("${sql.restaurant.order.findOrdersByUserId}")
    private String findOrdersByUserId;

    public List<Order> findOrdersByUserId(Long userId){
        return jdbcTemplate.query(findOrdersByUserId, new BeanPropertyRowMapper<>(Order.class), userId);
    }


    public Order createOrderInfo(Order order) {
        jdbcTemplate.update(createOrder,
                order.getUserId(),
                order.getRestaurant().getId(),
                order.getTotalMount(),
                order.getUser().getAddress(),
                order.getUniqueStr()
        );

        Map<String, Object> resultMap = jdbcTemplate.queryForMap(
                findOrderIdByUniqueStr,
                order.getUniqueStr()
        );

        // id 처리 (문자열로 캐스팅하지 말고 Number로 처리)
        Object idObj = resultMap.get("id");
        if (idObj instanceof Number) {
            order.setId(((Number) idObj).longValue());
        }

        // ordered_at 처리
        Object timeObj = resultMap.get("ordered_at");
        if (timeObj instanceof java.sql.Timestamp) {
            order.setLocalDateTime(((java.sql.Timestamp) timeObj).toLocalDateTime());
        }


        // 주문한 음식들을 db에 저장한다
        order.getMenus().forEach(orderMenu -> {
            createItem(OrderItem.builder()
                    .order(order)
                    .menu(orderMenu)
                    .quantity(orderMenu.getCount())
                    .priceAtOrder(orderMenu.getPrice())
                    .build());
        });

        return order;
    }

    public List<OrderItem> findItemsByOrderId(){

        return null;
    }

    // 주문한 내역을 저장한다
    public int createItem(OrderItem orderItem){
        return jdbcTemplate.update(createItem,
                orderItem.getOrder().getId(),
                orderItem.getMenu().getId(),
                orderItem.getQuantity(),
                orderItem.getPriceAtOrder()
                );
    }
}
