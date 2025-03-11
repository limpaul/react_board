package com.example.mywas.repository.order;

import com.example.mywas.domain.order.User;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository
public class UserRepository {
    Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final List<User>  users = new ArrayList<>();
    private Long nextId =  1L;

    public User save(User user){
        user.setId(nextId++);
        users.add(user);
        return user;
    }
    public List<User> findAll(){
        return users;
    }


    public User findByUsername(String username){
        if(!users.isEmpty()){

            return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username))
                    .findFirst()
                    .orElseGet(() -> {
                       logger.info("User with username '{}' not found", username);
                       return null;
                    });
        }else{
            logger.info("user size is zero");
        }
        return null;
    }
}
