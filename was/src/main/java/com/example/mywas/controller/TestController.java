package com.example.mywas.controller;

import com.example.mywas.service.RSAEncryptExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class TestController {

    @Autowired private HttpSession session;
    @Autowired private HttpServletResponse response;

    @GetMapping("/test")
    public String test(){
        System.out.println("Get() test");
        return "test";
    }
    @GetMapping("/getSession")
    public String getSession(){
        session.setAttribute("key", new Date().toString());
        return "session";
    }
    @PostMapping("/getPubKey")
    public String getPubKey(){
        return RSAEncryptExampleService.getPublicKey();
    }

}
