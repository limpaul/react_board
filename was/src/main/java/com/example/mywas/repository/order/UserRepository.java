package com.example.mywas.repository.order;

import com.example.mywas.domain.order.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository
public class UserRepository {
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
}
