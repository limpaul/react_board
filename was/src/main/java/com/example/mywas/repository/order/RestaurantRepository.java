package com.example.mywas.repository.order;

import com.example.mywas.domain.order.Menu;
import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.domain.order.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Repository
public class RestaurantRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.restaurant.save}")
    private String restaurantSave;

    private Logger logger = LoggerFactory.getLogger(RestaurantRepository.class);
    private final List<Restaurant> restaurants = new ArrayList<>();
    private Long nextId =  1L;

    public Restaurant save(Restaurant restaurant){
        // 사용자 등록이 안되고 식당만 등록할 경우 경우
        if(restaurant.getUser() == null){
            logger.debug("RestaurantRespoitory save()", "restraunt.user is null...");
            return null;
        }
        jdbcTemplate.update(restaurantSave,
                restaurant.getUser().getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getExplain());

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
