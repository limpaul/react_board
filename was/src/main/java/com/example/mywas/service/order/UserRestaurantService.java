package com.example.mywas.service.order;

import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.domain.order.User;
import com.example.mywas.repository.order.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRestaurantService implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(UserRestaurantService.class);
    @Autowired
    private RestaurantRepository restaurantRepository;


    public void enrollRestaurant(Restaurant restaurant) {
        if(restaurant.getUser() == null){
            logger.debug("UserRestaurantService enrollRestaurant() user is null");
        }
        logger.info("식당 등록: "+restaurant.toString());
        restaurantRepository.save(restaurant);
    }
    public void findAll(){
        restaurantRepository.findAll().forEach(restaurant -> {
            logger.info(restaurant.toString());
        });
    }

    @Override
    public void run(String... args) throws Exception {

        findAll();
    }
}
