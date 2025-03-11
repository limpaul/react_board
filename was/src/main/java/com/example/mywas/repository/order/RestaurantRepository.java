package com.example.mywas.repository.order;

import com.example.mywas.domain.order.Menu;
import com.example.mywas.domain.order.Restaurant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Repository
public class RestaurantRepository {
    private Logger logger = LoggerFactory.getLogger(RestaurantRepository.class);
    private final List<Restaurant> restaurants = new ArrayList<>();
    private Long nextId =  1L;
    public Restaurant save(Restaurant restaurant){
        restaurant.setId(nextId++);
        restaurants.add(restaurant);
        return restaurant;
    }
    public List<Restaurant> findAll(){
        return restaurants;
    }

    public Restaurant findRestaurantByName(String restaurantName) {
        return restaurants.stream().filter(restaurant
                -> restaurant.getName().trim().equalsIgnoreCase(restaurantName.trim()))
                .findFirst()
                .orElseGet(()->{
                      logger.info("menu {} not exist", restaurantName);
                      return null;
                });
    }
}
