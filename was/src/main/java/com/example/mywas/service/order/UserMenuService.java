package com.example.mywas.service.order;

import com.example.mywas.domain.order.Menu;
import com.example.mywas.repository.order.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMenuService implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(UserMenuService.class);
    @Autowired
    private MenuRepository menuRepository;

    public void enrollMenu(Menu menu) {
        menuRepository.save(menu);
    }
    public void findAllMenus(){
        menuRepository.findAll().forEach(menu ->{
            logger.info(menu.toString());
        });
    }
    public List<Menu> findMenuByRestaurantId(Long findMenuByRestaurantId){
        return menuRepository.findMenuByRestaurantId(findMenuByRestaurantId);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
