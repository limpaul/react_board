package com.example.mywas.configuration;

import com.TouchEn.mVaccine.web.servlet.MVaccineWebServlet;
import com.raonsecure.transkey.servlet.TranskeyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletRegistryConfig {
    @Bean
    public ServletRegistrationBean<TranskeyServlet> transkeyServlet(){
        ServletRegistrationBean<TranskeyServlet> sr = new ServletRegistrationBean<>(new TranskeyServlet());
        sr.addInitParameter("isRealPath","false");
        sr.addInitParameter("isClassPath","true");
        sr.addInitParameter("iniFilePath","/raon_config/config.ini");
        sr.addInitParameter("licenseIniPath","/raon_config/transkey_license.ini");
        sr.addUrlMappings("/transkeyServlet");
        sr.setLoadOnStartup(1);

        return sr;
    }
    @Bean
    public ServletRegistrationBean<MVaccineWebServlet> mVaccineWebServlet(){
        ServletRegistrationBean<MVaccineWebServlet> sr = new ServletRegistrationBean<>(new MVaccineWebServlet());
        sr.addInitParameter("isRealPath","false");
        sr.addInitParameter("isClassPath","true");
        sr.addInitParameter("iniFilePath","/raon_config/mVaccineWebConfig.ini");
        sr.addInitParameter("tokenKeyPath", "/raon_config/mvcVerifyToken.key");
        sr.addUrlMappings("/mvaccineforweb/*");
        sr.setLoadOnStartup(1);
        return sr;
    }
}
