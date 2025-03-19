package com.example.mywas.service.order;

import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.domain.order.User;
import com.example.mywas.repository.order.RestaurantRepository;
import com.example.mywas.repository.order.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class UserService implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired private UserRepository userRepository;

    @Autowired private RestaurantRepository restaurantRepository;

    public void enrollUser(Map<String, Object> dataMap) {
        String username = (String)dataMap.get("username");
        String useremail = (String)dataMap.get("useremail");
        String userpassword = (String)dataMap.get("userpassword");
        String restaurantName = (String)dataMap.get("restaurantName");
        String restaurantAddress = (String)dataMap.get("restaurantAddress");

        User user = User.builder()
                .username(username)
                .email(useremail)
                .password(userpassword)
                .build();
        if(restaurantName!=null && restaurantAddress!=null){
            user.setRole("ROLE_OWNER");
            User result = userRepository.save(user);
            // 식당 등록
            restaurantRepository.save(Restaurant.builder()
                            .user(result)
                            .address(restaurantAddress)
                            .build());
        }else{
            user.setRole("ROLE_CUSTOMER");
            userRepository.save(user);
        }
    }

    public Map<String, Object> loginUser(String username, String password){
        Map<String, Object> dataMap = new HashMap<>();
        User user = userRepository.findByUsername(username);
        if(user!=null && user.getPassword().equals(password)){
            dataMap.put("username", user.getUsername());
            dataMap.put("user", user.getEmail());
            dataMap.put("role", user.getRole());
            dataMap.put("isSuccess", true);
            dataMap.put("message", "로그인 성공");
        }else{
            dataMap.put("isSuccess", false);
            dataMap.put("message", "로그인 실패");
        }
        return dataMap;
    }
    public void enrollInitUser(User user) {
        userRepository.save(user);

    }
    // 사용자 추가

    // 사용자 탈퇴

    // 사용자 조회
    public void findUserAll(){
        userRepository.findAll().forEach(user -> {
            logger.info(user.toString());
        });
    }

    @Override
    public void run(String... args) throws Exception {
        // 기본 사용자 등록 (일반 사용자, 식당 관리자)
        User user1 = new User(1L, "test", "test@example.com", "1234", "ROLE_USER");
        User user2 = new User(2L, "Jeus", "johndoe@example.com", "1234", "ROLE_USER");
        User user3 = new User(3L, "JBoss", "bwlim@example.com", "1234", "ROLE_OWNER");
        User user4 = new User(4L, "Raon", "bwlim@example.com", "1234", "ROLE_OWNER");
        User user5 = new User(5L, "limpaul", "paul@example.com", "1234", "ROLE_OWNER");
        User user6 = new User(6L, "Dragon", "Dragon@example.com", "1234", "ROLE_OWNER");
        User user7 = new User(7L, "Jane Admin", "janeadmin@example.com", "adminpass", "ROLE_ADMIN");
        enrollInitUser(user1);
        enrollInitUser(user2);
        enrollInitUser(user3);
        enrollInitUser(user4);
        enrollInitUser(user5);
        enrollInitUser(user6);
        enrollInitUser(user7);
        findUserAll();
    }
}
