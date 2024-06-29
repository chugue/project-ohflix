package com.project.ohflix._core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KakaoPayConfig {

    private final String cid;
    private final String adminKey;
    private final String redirectUrl;

    public KakaoPayConfig(@Value("${kakao.pay.cid}") String cid,
                          @Value("${kakao.pay.admin-key}") String adminKey,
                          @Value("${kakao.pay.redirect-url}") String redirectUrl) {
        this.cid = cid;
        this.adminKey = adminKey;
        this.redirectUrl = redirectUrl;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders kakaoPayHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "KakaoAK " + adminKey);
        return headers;
    }

    public String getCid() {
        return cid;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}
