package com.example.mywas.service.order;

import com.example.mywas.configuration.JwtConfiguration;
import com.example.mywas.domain.order.Restaurant;
import com.example.mywas.domain.order.User;
import com.example.mywas.repository.order.RestaurantRepository;
import com.example.mywas.repository.order.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired private UserRepository userRepository;

    @Autowired private RestaurantRepository restaurantRepository;

    @Autowired private JwtConfiguration jwtConfiguration;

    public int enrollUser(Map<String, Object> dataMap) {
        String username = (String)dataMap.get("username"); // 사용자 아이디
        String useremail = (String)dataMap.get("useremail"); // 사용자 이메일
        String userpassword = (String)dataMap.get("userpassword"); // 사용자 계정 비번
        String restaurantname = (String)dataMap.get("restaurantName"); // 식당 이름
        String restaurantaddress = (String)dataMap.get("restaurantAddress");// 식당 주소
        String restaurantexplain = (String)dataMap.get("restaurantExplain"); // 식당 설명
        Boolean isChecked  = (Boolean) dataMap.get("isChecked"); // 판매용 계정 유무

        if(isChecked && restaurantname!=null && restaurantaddress!=null){
            // 식당 계정들 등록시킨다
            User user = User.builder()
                    .username(username)
                    .email(useremail)
                    .password(sha1(userpassword))
                    .role("ROLE_OWNER")
                    .build();

            userRepository.save(user);

            user = userRepository.findUserByUsername(user.getUsername());

            // 식당 계정으로 부터 반환된 사용자 아이디 값으로 식당도 등록 시킨다
            Restaurant restaurant = Restaurant.builder()
                    .user(user)
                    .name(restaurantname)
                    .address(restaurantaddress)
                    .explain(restaurantexplain)
                    .build();

            restaurantRepository.save(restaurant);

            return 1; // 식당 주인일 경우 1번을 반환
        }else{
            userRepository.save(User.builder()
                    .username(username)
                    .email(useremail)
                    .password(sha1(userpassword))
                    .role("ROLE_CUSTOMER")
                    .build());
            return 2; // 일반 계정일 경우 2번으로 반환
        }

    }

    public Map<String, Object> loginUser(String username, String password){
        Map<String, Object> dataMap = new HashMap<>();
        User user = userRepository.findByUsername(username);
        logger.debug("from client password: "+sha1(password));
        logger.debug("from db client password: "+user.getPassword());
        if(user!=null && user.getPassword().equals(sha1(password))){
            dataMap.put("username", user.getUsername());
            dataMap.put("user", user.getEmail());
            dataMap.put("role", user.getRole());
            dataMap.put("token", jwtConfiguration.createToken(user));
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

    public static String sha1(String input) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(input.getBytes("UTF-8"));

            // 바이트 배열을 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));  // 2자리 hex로 변환
            }

            return hexString.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
