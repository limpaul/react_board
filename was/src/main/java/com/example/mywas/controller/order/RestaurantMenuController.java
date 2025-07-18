package com.example.mywas.controller.order;

import com.example.mywas.domain.order.Menu;
import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.repository.order.MenuRepository;
import com.example.mywas.repository.order.RestaurantRepository;
import com.example.mywas.repository.order.UserRepository;
import com.example.mywas.service.order.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantMenuController {

    @Autowired private UserRepository userRepository;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private MenuRepository menuRepository;
    @Autowired private UserMenuService menuService;

    @GetMapping("/api/order/user/enroll/menu")
    public String MenuEnrollController(){
        return "{}";
    }

    @GetMapping("/api/order/menu/list/{restaurantId}")
    public List<Menu> findMenuByRestaurantId(@PathVariable Long restaurantId){
        return menuService.findMenuByRestaurantId(restaurantId);
    }
    @GetMapping("/api/order/user/enroll/menu/test")
    public String MenuEnrollControllerTest(){ //메뉴 등록
        // 해당 식당이 존재 하는지
        Restaurant restaurant = restaurantRepository.findRestaurantByName("paul bassett");

        if(restaurant != null){
            // 메뉴 데이터 받는다
            Menu menu = Menu.builder()
                    .name("피자")
                    .price(20000)
                    .description("Good Pizza")
                    .restaurant(restaurant)
                    .build();
            Menu menu2 = Menu.builder()
                    .name("치킨")
                    .price(30000)
                    .description("Good Chicken")
                    .restaurant(restaurant)
                    .build();

            // 사용자 확인 - 메뉴를 등록한 사용자가 식당 주인이 맞는가
            if(restaurant.getUser().getUsername().equals("paul")){

            }
            // 메뉴 등록
            menuRepository.save(menu); // 피자 등록
            menuRepository.save(menu2); // 치킨 등록  
        }


        return "{}";
    }
}
