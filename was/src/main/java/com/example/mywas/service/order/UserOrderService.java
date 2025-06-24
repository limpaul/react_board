package com.example.mywas.service.order;

import com.example.mywas.configuration.JwtConfiguration;
import com.example.mywas.domain.order.*;
import com.example.mywas.repository.order.OrderRepository;
import com.example.mywas.repository.order.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;

@Service
public class UserOrderService {

    private static final Logger logger = LoggerFactory.getLogger(UserOrderService.class);
    @Autowired private OrderRepository orderRepository;

    @Autowired private UserRepository userRepository;
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
        User user = userRepository.findUserByUserId(order.getUserId());
        if(user != null){
            order.setUser(user);
        }else{
            logger.debug("user is null");
        }
        List<Menu> menus;

        dataBody.forEach((key, value) -> {
            switch (key) {
                case "restaurantInfo":
                    Restaurant restaurant = objectMapper.convertValue(value, Restaurant.class);
                    order.setRestaurant(restaurant);
                    break;

                case "menuData":
                    List<Menu> getMenus = objectMapper.convertValue(value, new TypeReference<List<Menu>>() {});
                    order.setMenus(getMenus);
                    break;

                case "totalMount":
                    order.setTotalMount((Integer) value);
                    break;

                case "orderCount":
                    order.setOrderCount((Integer) value);
                    break;

                case "address":
                    if (order.getUser() != null) {
                        order.getUser().setAddress((String) value);
                    }
                    break;

                default:
                    // 필요시 로그 출력 or 무시
                    break;
            }
        });

        order.setUniqueStr(UUID.randomUUID().toString()); // 주문 코유 코드를 부여한다
        // 해당 데이터를 주문 테이블에 저장한다
        System.out.println(order.toString());
        // orders 테이블에 필요 값들을 저장하고 id값 정보를 리턴한다
        Order resultOrderInfo = orderRepository.createOrderInfo(order);
        // order 정보로 order_items 테이블 정보를 갱신하다
        //resultOrderInfo.getMenus().
        order.getMenus().forEach(menu ->{
            System.out.println(menu.toString());
        });

        return resultMap;
    }
}
