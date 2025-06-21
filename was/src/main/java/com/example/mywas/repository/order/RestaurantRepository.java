package com.example.mywas.repository.order;

import com.example.mywas.configuration.JwtConfiguration;
import com.example.mywas.domain.order.Restaurant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Repository
public class RestaurantRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Value("${sql.restaurant.save}")
    private String restaurantSave;

    @Value("${sql.restaurant.findAll}")
    private String findAll;

    @Value("${sql.restaurant.findRestaurantByUserId}")
    private String findRestaurantByUserId;

    @Value("${sql.restaurant.deleteRestaurantByUserId}")
    private String deleteRestaurantByUserId;

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

    // 사용자가 토큰으로 조회시
    public List<Restaurant> findRestaurantByUserToken(String token){
        // 토근 확인
        Map<String, Object> resultMap = jwtConfiguration.getTokenInfo(token);
        if(resultMap == null){ // 토큰이만료 되었거나 유효하지 않을 경우
            // code, data, success, message
            return null;
        }
        Float userId = Float.valueOf(resultMap.get("userid").toString());
        // 등록된 식당을 로드한다
        try{
            return jdbcTemplate.query(findRestaurantByUserId, new BeanPropertyRowMapper<>(Restaurant.class), userId);
        }catch (EmptyResultDataAccessException e){
            return  null;
        }
    }
    public List<Restaurant> findRestaurantByUserId(Long userId){
        // 등록된 식당을 로드한다
        try{
            return jdbcTemplate.query(findRestaurantByUserId, new BeanPropertyRowMapper<>(Restaurant.class), userId);
        }catch (EmptyResultDataAccessException e){
            return  null;
        }
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

    public List<Restaurant> removeRestaurant(List<Restaurant> restaurants) {
        List<Restaurant> errorList = new ArrayList<>();
        try{
            restaurants.forEach(restaurant -> {
                int result = jdbcTemplate.update(deleteRestaurantByUserId, restaurant.getUserId(), restaurant.getId());
                if(result == 0){
                    errorList.add(restaurant);
                }
            });
            return errorList;
        }catch (DataAccessException e){
            return null;
        }
    }
}
