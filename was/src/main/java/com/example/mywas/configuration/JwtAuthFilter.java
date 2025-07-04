package com.example.mywas.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthFilter implements Filter {

    private final JwtConfiguration jwtConfiguration;

    public JwtAuthFilter(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }


    /*
        JwtFilterRegistryConfig 에 등록된 URL패턴
        /order/user/shopping/* 으로 요청하였을 때 Filter에서 바로 접근하지 못하게 설정하기 위함
    */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter!!!!!!!!!!!");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String authHeader = request.getHeader("Authorization");
        Map<String, Object> dataMap = new HashMap<>();
        if(authHeader!=null){
            System.out.println("인증헤더: "+authHeader);
            Boolean tokenVerifyResult = jwtConfiguration.validateToken(authHeader.split(" ")[1]);
            if(tokenVerifyResult == true){
                dataMap.put("authentication",true);
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(dataMap);
                response.getWriter().println(jsonStr);
                response.flushBuffer();
            }else{
                dataMap.put("authentication",false);
                dataMap.put("redirectTo","/order/user/login");
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(dataMap);
                response.getWriter().println(jsonStr);
                response.flushBuffer();
            }
        }else{
            System.out.println(request.getRequestURI()+" 의 잘못된 접근");
            response.setContentType("application/json;charset=utf-8");
            dataMap.put("authentication",false);
            dataMap.put("redirectTo","/order/user/login");
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(dataMap);
            response.getWriter().println(jsonStr);
            response.flushBuffer();
        }
    }
}

