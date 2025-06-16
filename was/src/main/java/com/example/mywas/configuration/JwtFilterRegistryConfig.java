package com.example.mywas.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtFilterRegistryConfig {
    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtFilterRegistration(JwtConfiguration jwtUtil) {
        FilterRegistrationBean<JwtAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new JwtAuthFilter(jwtUtil));
        registration.addUrlPatterns(
                "/order/user/shopping/*",
                "/order/user/api/checkLogin"
        );
        registration.setOrder(1); // 필터 순서 설정
        return registration;
    }
}
