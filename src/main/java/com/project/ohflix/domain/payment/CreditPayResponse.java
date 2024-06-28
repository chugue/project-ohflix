package com.project.ohflix.domain.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class CreditPayResponse {

    @Data // getter, setter
    public static class TokenDTO {

        private String code;

        private String message;

        private Response response;

        @Data
        class Response {
            @JsonProperty("access_token")
            private String accessToken;
            private String now;
            @JsonProperty("expired_at")
            private String expiredAt;
        }
    }
}
