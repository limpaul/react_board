package com.example.mywas.controller.order;

import com.example.mywas.service.order.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RestaurantUserOrderController {
    @Autowired private UserOrderService userOrderService;
/*
* 사용자가 /order/user/shopping/cart 에서 결제를 진행
*/
    @PostMapping("/api/order/user/order")
    public String userOrderRequest(
            @RequestHeader Map<String, Object> dataHeader,
            @RequestBody Map<String, Object> dataBody){

        userOrderService.userOrderToRestaurant(dataHeader, dataBody);

        return "{}";
    }
    @GetMapping("/api/order/user/order/complete/list")
    public Map<String, Object> userOrderCompleteList(
            @RequestHeader Map<String, Object> dataHeader){

        return userOrderService.findOrderByUserId(dataHeader, null);
    }
}
