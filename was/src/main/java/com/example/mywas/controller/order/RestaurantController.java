package com.example.mywas.controller.order;

import com.example.mywas.domain.order.Menu;
import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.domain.order.User;
import com.example.mywas.service.order.UserMenuService;
import com.example.mywas.service.order.UserRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    @Autowired
    private UserRestaurantService userRestaurantService;




    // 식당 등록
    @PostMapping("/api/order/user/enroll/restaurant")
    public String userEnrollController(@RequestBody Restaurant restaurant){
        userRestaurantService.enrollRestaurant(restaurant);
        return "{}";
    }

    // 식당 삭제

    // 식당 조회

}
