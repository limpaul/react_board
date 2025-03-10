package com.example.mywas.repository.order;

import com.example.mywas.domain.order.Menu;
import com.example.mywas.domain.order.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString
@Repository
public class MenuRepository {
    private final List<Menu> menus = new ArrayList<>();
    private Long nextId =  1L;
    public Menu save(Menu menu){
        menu.setId(nextId++);
        menus.add(menu);
        return menu;
    }
    public List<Menu> findAll(){
        return menus;
    }
}
