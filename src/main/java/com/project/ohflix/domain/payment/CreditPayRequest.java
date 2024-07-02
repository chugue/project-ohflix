package com.project.ohflix.domain.payment;

import com.project.ohflix.domain._enums.Paymethod;
import lombok.Data;

import java.sql.Timestamp;

public class CreditPayRequest {

    @Data
    public static class CreditPayDTO {
        private Timestamp createdAt;
        private String description;
        private String servicePeriod;
//        private Paymethod paymethod;
        private String cardNumber;
        private Integer amount;
//        private Integer vat;
//        private Integer supplyValue;
    }

    @Data
    public static class CreditPayTestDTO {
        private String merchantUid; // 클라이언트에서 전송
        private Timestamp paymentDate; // 클라이언트에서 전송 (혹은 서버에서 현재 시간으로 설정)
        private Integer cardInfoId;

    }
}
