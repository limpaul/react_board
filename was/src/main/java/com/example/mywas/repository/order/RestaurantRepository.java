package com.example.mywas.repository.order;

import com.example.mywas.domain.order.Restaurant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    @Value("${sql.restaurant.findAll}")
    private String findAll;

    @Value("${sql.restaurant.findRestaurantByUserId}")
    private String findRestaurantByUserId;

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
                restaurant.getDescription());

        return restaurant;
    }
    public List<Restaurant> findAll(){
        return jdbcTemplate.query(
                findAll,
                new BeanPropertyRowMapper<>(Restaurant.class)
        );
    }

    public List<Restaurant> findRestaurantByUserId(Long userId){
        // 해당 사용자 아이디로 조회시 권한이 식당 소유주로 되어있는가?
        
        // 등록된 식당을 로드한다
        return null;
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
