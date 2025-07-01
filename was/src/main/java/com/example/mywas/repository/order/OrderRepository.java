package com.example.mywas.repository.order;

import com.example.mywas.domain.order.Order;
import com.example.mywas.domain.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    @Value("${sql.restaurant.order.findOrdersByUserId}")
    private String findOrdersByUserId;
    @Value("${sql.restaurant.order.findOrderByRestaurantId}")
    private String findOrderByRestaurantId;

    @Value("${sql.restaurant.order.updateStatusByUniqueStr}")
    private String updateStatusByUniqueStr;

    public List<Map<String, Object>> findOrdersByUserId(Long userId){
        //return jdbcTemplate.query(findOrdersByUserId, new BeanPropertyRowMapper<>(Order.class), userId);
        return  jdbcTemplate.queryForList(findOrdersByUserId, userId);
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
            LocalDateTime ldt = ((java.sql.Timestamp) timeObj).toLocalDateTime();
            // 시스템 기본 존을 기준으로 OffsetDateTime 생성 (원하는 존으로 변경 가능)
            order.setOrderedAt(ldt.atOffset(ZoneOffset.systemDefault().getRules().getOffset(ldt)));
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

    // 주문한 내역을 저장한다
    public int createItem(OrderItem orderItem){
        return jdbcTemplate.update(createItem,
                orderItem.getOrder().getId(),
                orderItem.getMenu().getId(),
                orderItem.getQuantity(),
                orderItem.getPriceAtOrder()
                );
    }

    public List<Order> findOrdersByRestaurantId(Long restaurantId) {
        try{
            return jdbcTemplate.query(findOrderByRestaurantId, new BeanPropertyRowMapper<>(Order.class),restaurantId);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public Boolean acceptOrder(Map<String, Object> dataBody) {
        int result = jdbcTemplate.update(updateStatusByUniqueStr, "accept", (String)dataBody.get("uniqueStr"));
        if(result > 0) {
            return true;
        }else{
            return false;
        }
    }
    public Boolean cancelOrder(Map<String, Object> dataBody) {
        int result = jdbcTemplate.update(updateStatusByUniqueStr, "cancel", (String)dataBody.get("uniqueStr"));
        if(result > 0) {
            return true;
        }else{
            return false;
        }
    }
}
