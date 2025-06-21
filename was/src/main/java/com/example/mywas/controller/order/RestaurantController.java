package com.example.mywas.controller.order;

import com.example.mywas.domain.order.Menu;
import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.domain.order.User;
import com.example.mywas.repository.order.RestaurantRepository;
import com.example.mywas.repository.order.UserRepository;
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
