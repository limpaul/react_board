package com.example.mywas.service.order;

import com.example.mywas.configuration.JwtConfiguration;
import com.example.mywas.domain.order.Menu;
import com.example.mywas.domain.order.Order;
import com.example.mywas.domain.order.OrderItem;
import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.repository.order.OrderRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserOrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private JwtConfiguration jwtConfiguration;

    @Autowired private ObjectMapper objectMapper;

    public Map<String, Object> userOrderToRestaurant(Map<String, Object> dataHeader, Map<String, Object> dataBody) {
        Map<String, Object> tokenInfo = jwtConfiguration.getTokenInfo(dataHeader);
        Map<String, Object> resultMap = new HashMap<>();
        if(tokenInfo == null){ // token 정보가 없다면
            resultMap.put("status", false);
            resultMap.put("message", "주문을 실패 하였습니다");
            return resultMap;
        }
        // token 으로 검증 받은 사용자 정보
        Long userId = Long.valueOf((Integer)tokenInfo.get("userid"));

        Order order = new Order(); // 주문목록 정보 클래스
        order.setUserId(userId); // 사용자 정보 입력
        List<Menu> menus;

        dataBody.forEach((key, value)->{
            // 가게 정보 추출
            if(key.equals("restaurantInfo")){
                Restaurant restaurant = objectMapper.convertValue(value, Restaurant.class);
                order.setRestaurant(restaurant);
            }
            // 메뉴 정보 추출
            if(key.equals("menuData")){
                order.setMenus(objectMapper.convertValue(value, new TypeReference<List<Menu>>() {}));
            }
            // 총 금액이 얼마인지
            if(key.equals("totalMount")){
                order.setTotalMount((Integer)dataBody.get("totalMount"));
            }
            if(key.equals("orderCount")){
                order.setOrderCount((Integer)dataBody.get("orderCount"));
            }

        });

        // 해당 데이터를 주문 테이블에 저장한다
        System.out.println(order.toString());
        // orders 테이블에 필요 값들을 저장하고 id값 정보를 리턴한다
        //Order resultOrderInfo = orderRepository.createOrderInfo(order);
        // order 정보로 order_items 테이블 정보를 갱신하다
        //resultOrderInfo.getMenus().

        return resultMap;
    }
}
