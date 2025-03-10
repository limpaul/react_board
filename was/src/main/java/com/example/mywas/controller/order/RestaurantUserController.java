package com.example.mywas.controller.order;

import com.example.mywas.domain.order.User;
import com.example.mywas.repository.order.UserRepository;
import com.example.mywas.service.order.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Getter
@Setter
@ToString
@RestController
public class RestaurantUserController {
    @Autowired private UserService userService;

    @Autowired private UserRepository userRepository;
    //회원 가입
    @PostMapping("/api/order/user/enroll/account")
    public String userEnrollController(@RequestBody User user){
        userService.enrollUser(user);
        return "{}";
    }

    // 회원 조회
    @GetMapping("/test2")
    public List<User> test2(){
        return userRepository.findAll();
    }

    // 회원 탈퇴
}
