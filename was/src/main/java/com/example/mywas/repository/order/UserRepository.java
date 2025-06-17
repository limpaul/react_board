package com.example.mywas.repository.order;

import com.example.mywas.domain.order.User;
import lombok.Getter;
import lombok.Setter;
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
@Repository
public class UserRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.user.save}") // INSERT INTO users...
    private String userAddSql;

    @Value("${sql.user.findAll") // 'select * from users'
    private String userFindAll;

    @Value("${sql.user.findUserByUserName}") // 'select * from users where username = ?'
    private String findUserByUserName;

    @Value("${sql.user.findUserByUserNameAndRole}") // 'select * from users where username = ? and role = ?'
    private String findUserByUserNameAndRole;

    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public User save(User user){
        jdbcTemplate.update(userAddSql,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
        return user;
    }
    public List<User> findAll(){
        return jdbcTemplate.query(userFindAll, new BeanPropertyRowMapper<>(User.class));
    }


    public User findByUsername(String username){
        // 단일행 조회
        try{
            return jdbcTemplate.queryForObject(
                    findUserByUserName,
                    new BeanPropertyRowMapper<>(User.class),
                    username
            );
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public User findUserByUsernameAndRole(String username, String role){
        // 단일행 조회
        return jdbcTemplate.queryForObject(
                findUserByUserNameAndRole,
                new BeanPropertyRowMapper<>(User.class),
                username,
                role
        );
    }

}
