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
}
