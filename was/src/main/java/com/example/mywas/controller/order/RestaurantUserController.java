package com.example.mywas.controller.order;

import com.example.mywas.domain.order.User;
import com.example.mywas.repository.order.UserRepository;
import com.example.mywas.service.order.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@RestController
public class RestaurantUserController {
    @Autowired private UserService userService;

    @Autowired private UserRepository userRepository;

    Logger log = LoggerFactory.getLogger(RestaurantUserController.class);
    //회원 가입
    @PostMapping("/api/order/user/enroll/account")
    public String userEnrollController(@RequestBody Map<String, Object> dataMap){
        log.info(dataMap.toString());
        userService.enrollUser(dataMap);
        return "{}";
    }

    @PostMapping("/api/order/user/login")
    public Map<String, Object> userLoginController(@RequestBody Map<String, String> datamap){
        String username = (String)datamap.get("username");
        String userpassword = (String)datamap.get("userpassword");
        return userService.loginUser(username, userpassword);
    }

    // test 계정 사용자 등록 테스트
    @GetMapping("/api/order/user/enroll/account/test")
    public String createRandomUser(){
        User user = new User(userRepository.getNextId()+1, "test", "test@example.com", "1234", "ROLE_USER");
        userRepository.save(user);
        return "{}";
    }
    // 회원 조회 테스트
    @GetMapping("/test2")
    public List<User> test2(){
        log.info(userRepository.getNextId().toString());
        return userRepository.findAll();
    }

    // 회원 탈퇴
}
