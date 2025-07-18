package com.example.mywas.controller.order;

import com.example.mywas.domain.order.Menu;
import com.example.mywas.domain.order.Order;
import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.domain.order.User;
import com.example.mywas.repository.order.RestaurantRepository;
import com.example.mywas.repository.order.UserRepository;
import com.example.mywas.service.order.UserManagerService;
import com.example.mywas.service.order.UserMenuService;
import com.example.mywas.service.order.UserRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RestaurantController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRestaurantService userRestaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserManagerService userManagerService;


    // 식당 등록
    @PostMapping("/api/order/user/enroll/restaurant")
    public String userEnrollRestaurantController(@RequestBody Restaurant restaurant){ //음식점  name, address , user
        userRestaurantService.enrollRestaurant(restaurant);
        return "{}";
    }

    // 식당 등록 테스트
    @GetMapping("/api/order/user/enroll/restaurant/test")
    public String userEnrollRestaurantControllerTest(){// 음식점  name, address , user
        User findUser = userRepository.findByUsername("limpaul");
        Restaurant restaurant = Restaurant.builder()
                .name("paul bassett")
                .address("orudong")
                .user(findUser)
                .build();

        if(findUser != null){ // 사용자 식당 등록
            userRestaurantService.enrollRestaurant(restaurant);
        }
        return "{}";
    }

    // 식당 삭제
    @PostMapping("/api/order/user/manager/restaurant/delete")
    public Map<String, Object> deleteRestaurantByUserId(
            @RequestHeader Map<String, Object> dataHeader,
            @RequestBody Map<String, Object> dataMap){

        return userRestaurantService.removeRestaurant(dataHeader, dataMap);
    }

    //식당 관리자 주문 조회
    @GetMapping("/api/order/user/manager/order/list/{restaurantId}")
    public List<Order> findRestaurantOrderById (@RequestHeader Map<String, Object> dataHeader, @PathVariable Long restaurantId){
        return userManagerService.findOrdersById(dataHeader, restaurantId);
    }

    // 식당 관리자 받은 주문 수락
    @PostMapping("/api/order/user/manager/order/accept")
    public Map<String, Object> orderAccept(@RequestHeader Map<String, Object> dataHeader,
                                           @RequestBody Map<String, Object> dataBody){

        return userManagerService.acceptOrder(dataHeader, dataBody);
    }

    // 식당 관리자 받은 주문 취소
    @PostMapping("/api/order/user/manager/order/cancel")
    public Map<String, Object> orderCancel(@RequestHeader Map<String, Object> dataHeader,
                                           @RequestBody Map<String, Object> dataBody){

        return userManagerService.cancelOrder(dataHeader, dataBody);
    }

    // 식당 조회
    @GetMapping("/api/order/user/manager/restaurant/list")
    public List<Restaurant> findRestaurantByUserId (@RequestHeader Map<String, Object> dataHeader){
        return restaurantRepository.findRestaurantByUserToken(dataHeader);
    }

    @GetMapping("/api/order/user/restaurant/list")
    public List<Restaurant> findEnrollRestaurants(){
        return userRestaurantService.findAll();
    }
    @GetMapping("/api/order/user/find/restaurant/test")
    public String findEnrollRestaurant(){
        return restaurantRepository.findAll().toString();
    }
}
