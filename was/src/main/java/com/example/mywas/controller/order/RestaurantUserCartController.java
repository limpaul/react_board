package com.example.mywas.controller.order;

import com.example.mywas.domain.order.*;
import com.example.mywas.repository.order.CartRepository;
import com.example.mywas.repository.order.MenuRepository;
import com.example.mywas.repository.order.RestaurantRepository;
import com.example.mywas.repository.order.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantUserCartController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // 카트 담기
    @GetMapping("/api/order/user/add/cart/item")
    public String userAddCartItemTest(){

        // 어느 고객이
        User customer = userRepository.findByUsername("limpaul");

        // 어느 음식점에서
        Restaurant selectRestaurant = restaurantRepository.findRestaurantByName("paul bassett");

        // 어느 메뉴 들을 선택 / 음식점 이름을 통해서
        Menu selectMenu = menuRepository.findMenuByRestaurantName(selectRestaurant.getName());

        

        return "{}";
    }

}
