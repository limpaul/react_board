package com.example.mywas.service.order;

import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.domain.order.User;
import com.example.mywas.repository.order.RestaurantRepository;
import com.example.mywas.repository.order.UserRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRestaurantService  {
    Logger logger = LoggerFactory.getLogger(UserRestaurantService.class);
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void enrollRestaurant(Restaurant restaurant) {
        if(restaurant.getUser() == null){
            logger.debug("UserRestaurantService enrollRestaurant() user is null");
        }
        logger.info("식당 등록: "+restaurant.toString());
        restaurantRepository.save(restaurant);
    }
    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }
    public List<Restaurant> findRestaurantByUserId(Long userId){
        if(userId != null){ // 사용자 아이디가 null일 경우
            return null;
        }
        // 사용자 아이디 조회
        User user = userRepository.findUserByUserId(userId);
        if(user != null){
            // 가게 검색
            List<Restaurant> restaurants = restaurantRepository.findRestaurantByUserId(user.getId());
            if(restaurants != null){ // 식당주인이 등록한 가게가 있다면
                return restaurants;
            }else{
                return null;
            }
        }
        return null;
    }

    public Map<String, Object> removeRestaurant(Map<String, Object> dataHeader, Map<String, Object> dataMap) {

        List<Restaurant> restaurants  = new ArrayList<>();

        List<Map<String, Object>> deleteList = (List<Map<String, Object>>)dataMap.get("deleteList");
        deleteList.forEach(restaurantStr->{
            Restaurant restaurant = objectMapper.convertValue(restaurantStr, Restaurant.class);
            restaurants.add(restaurant);
        });
        List<Restaurant> errorList = restaurantRepository.removeRestaurant(restaurants);
        Map<String, Object> resultMap = new HashMap<>();
        if(!errorList.isEmpty()){
            resultMap.put("message", errorList);
            resultMap.put("status", false);
        }else{
            resultMap.put("status", true);
        }
        return resultMap;
    }
}
