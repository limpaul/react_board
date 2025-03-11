package com.example.mywas.service.order;

import com.example.mywas.domain.order.User;
import com.example.mywas.repository.order.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


@Service
public class UserService implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired private UserRepository userRepository;
    public void enrollUser(User user) {
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
        User user1 = new User(1L, "JohnDoe", "johndoe@example.com", "1234", "ROLE_USER");
        User user2 = new User(2L, "Jeus", "johndoe@example.com", "1234", "ROLE_USER");
        User user3 = new User(3L, "JBoss", "bwlim@example.com", "1234", "ROLE_OWNER");
        User user4 = new User(4L, "Raon", "bwlim@example.com", "1234", "ROLE_OWNER");
        User user5 = new User(5L, "limpaul", "paul@example.com", "1234", "ROLE_OWNER");
        User user6 = new User(6L, "Dragon", "Dragon@example.com", "1234", "ROLE_OWNER");
        User user7 = new User(7L, "Jane Admin", "janeadmin@example.com", "adminpass", "ROLE_ADMIN");
        enrollUser(user1);
        enrollUser(user2);
        enrollUser(user3);
        enrollUser(user4);
        enrollUser(user5);
        enrollUser(user6);
        enrollUser(user7);
        userRepository.setNextId(7L);
        findUserAll();
    }
}
