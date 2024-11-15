package com.example.mywas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Service
public class RSAEncryptExampleService {
    @Autowired private static PrivateKey privateKey;

    @Autowired private static PublicKey publicKey;

    RSAEncryptExampleService() throws Exception {
        System.out.println("initialize RSA Key!!!!");
        generateRSAKeyPair();
    }

    //RSA키 생성
    public static void generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    // 공개키 반환
    public static String getPublicKey(){
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

}
