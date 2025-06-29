package com.example.mywas.service.order;

import com.example.mywas.configuration.JwtConfiguration;
import com.example.mywas.domain.order.Order;
import com.example.mywas.domain.order.OrderItem;
import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.domain.order.dto.OrderMenu;
import com.example.mywas.repository.order.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserManagerService {
    private static final Logger logger = LoggerFactory.getLogger(UserManagerService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired private JwtConfiguration jwtConfiguration;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private ItemRepository itemRepository;
    @Autowired private MenuRepository menuRepository;
    // 헤더에 대한 토큰 정보를 체크한다
    private Map<String, Object> commonHeaderCheck(Map<String, Object> dataHeader, String message){
        Map<String, Object> tokenInfo = jwtConfiguration.getTokenInfo(dataHeader);
        Map<String, Object> resultMap = new HashMap<>();
        if(tokenInfo == null){ // token 정보가 없다면
            resultMap.put("status", false);
            resultMap.put("message", message);
            return resultMap;
        }
        tokenInfo.put("status", true);
        return tokenInfo;
    }


    public List<Order> findOrdersById(Map<String, Object> dataHeader, Long restaurantId) {
        Map<String, Object> tokenInfo = commonHeaderCheck(dataHeader, "식당 주문 조회 권한이 없습니다");
        if((Boolean)tokenInfo.get("status")){
            // 조회한 사용자 아이디
            Long userid = Long.valueOf((Integer)tokenInfo.get("userid"));

            // 주문 테이블을 조회 한다
            List<Order> orderList = orderRepository.findOrdersByRestaurantId(restaurantId);
            // 주문 목록을 조회 한다
            orderList.forEach(order -> {
                List<OrderItem> orderItems = new ArrayList<>(); // 메뉴를 넣기 위해서 Order.List<OrderItem> set 해줘야 함
                List<Map<String, Object>> items = itemRepository.findItemsByOrderId(order.getId());
                items.forEach(item -> { // orders_item 리스트 반환

                    // Order -> List<OrderItem>, List<OrderMenu>
                    OrderMenu orderMenu = menuRepository.findMenuById((Long)item.get("MENU_ID"));
                    orderItems.add(OrderItem.builder()
                            .id((Long)item.get("id"))
                            .priceAtOrder((Integer)item.get("price"))
                            .menu(orderMenu)
                            .build());

                });
                order.setItems(orderItems);
            });

            // 반환 한다
            return orderList;
        }

        return null;
    }
}
