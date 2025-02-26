package com.lima.GerenciamentoDePlanos.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class RSAKeyConfig {

    @Value("${jwt.public-key-path}")
    private Resource publicKeyResource;

    @Value("${jwt.private-key-path}")
    private Resource privateKeyResource;

    @Bean
    public RSAPublicKey publicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyPem = readKeyContent(publicKeyResource);
        return parsePublicKey(publicKeyPem);
    }

    @Bean
    public RSAPrivateKey privateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyPem = readKeyContent(privateKeyResource);
        return parsePrivateKey(privateKeyPem);
    }

    private String readKeyContent(Resource resource) throws IOException {
        byte[] keyBytes = resource.getInputStream().readAllBytes();
        return new String(keyBytes, StandardCharsets.UTF_8);
    }

    private RSAPublicKey parsePublicKey(String publicKeyPem) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyContent = publicKeyPem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(publicKeyContent);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    private RSAPrivateKey parsePrivateKey(String privateKeyPem) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyContent = privateKeyPem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(privateKeyContent);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(spec);
    }
}