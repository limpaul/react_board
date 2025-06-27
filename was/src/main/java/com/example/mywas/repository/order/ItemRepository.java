package com.example.mywas.repository.order;

import com.example.mywas.domain.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    @Autowired private JdbcTemplate jdbcTemplate;
    @Value("${sql.restaurant.item.findItemsByOrderId}")
    private String findItemsByOrderId;

    public List<Map<String, Object>> findItemsByOrderId(Long orderId){
        return jdbcTemplate.queryForList(findItemsByOrderId, orderId);
    }
}
