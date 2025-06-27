package com.example.mywas.repository.order;

import com.example.mywas.domain.order.Menu;
import com.example.mywas.domain.order.User;
import com.example.mywas.domain.order.dto.OrderMenu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString
@Repository
public class MenuRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.menu.findMenuByRestaurantId}")
    private String findMenuByRestaurantId;
    @Value("${sql.menu.findMenuById}")
    private String findMenuById;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    private final List<Menu> menus = new ArrayList<>();
    private Long nextId =  1L;
    public Menu save(Menu menu){
        menu.setId(nextId++);
        menus.add(menu);
        logger.info("menu 등록 {}: ",menu.toString());
        return menu;
    }

    public List<Menu> findMenuByRestaurantId(Long restaurantId){
        try{
            return jdbcTemplate.query(findMenuByRestaurantId,
                    new BeanPropertyRowMapper<>(Menu.class),
                    restaurantId
            );
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public List<Menu> findAll(){
        return menus;
    }

    public Menu findMenuByRestaurantName(String name) {
        return menus.stream().filter(menu -> menu.getName().trim().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElseGet(()->{
                    logger.info("can`t not find restaurant {}", name);
                   return null;
                });
    }

    public OrderMenu findMenuById(Long menuId) {
        try{
            return jdbcTemplate.queryForObject(
                    findMenuById,
                    new BeanPropertyRowMapper<>(OrderMenu.class),
                    menuId
            );
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
