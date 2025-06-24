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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Repository
public class UserRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.user.save}") // INSERT INTO users...
    private String userAddSql;

    @Value("${sql.user.findAll}") // 'select * from users'
    private String userFindAll;

    @Value("${sql.user.findUserByUserId}")
    private String findUserByUserId;

    @Value("${sql.user.findUserByUserName}") // 'select * from users where username = ?'
    private String findUserByUserName;

    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public int save(User user) {
        return jdbcTemplate.update(
            userAddSql,
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getRole());

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

    public User findUserByUsername(String username){
        // 단일행 조회
        return jdbcTemplate.queryForObject(
                findUserByUserName,
                new BeanPropertyRowMapper<>(User.class),
                username
        );
    }

    public User findUserByUserId(Long userId){
        // 단일행 조회
        return jdbcTemplate.queryForObject(
                findUserByUserId,
                new BeanPropertyRowMapper<>(User.class),
                userId
        );
    }
}
