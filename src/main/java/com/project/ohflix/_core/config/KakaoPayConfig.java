package com.project.ohflix._core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KakaoPayConfig {
    @Value("${kakao.pay.cid}")
    private String cid;

    @Value("${kakao.pay.secret-key}")
    private String secretKey;

    @Value("${kakao.pay.redirect-url}")
    private String redirectUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders kakaoPayHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "KakaoAK " + secretKey);
        return headers;
    }

    public String getCid() {
        return cid;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}