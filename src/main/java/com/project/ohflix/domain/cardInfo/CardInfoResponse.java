package com.project.ohflix.domain.cardInfo;


import lombok.Data;

import java.time.LocalDate;

public class CardInfoResponse {

    //paymethod-update page
    @Data
    public static class DetailDTO {
        private Integer cardInfoId;
        private String cardNumber;
        private String lastDigit;
        private String expiryMonth;
        private String cardOwner;
        private LocalDate dateOfBirth;
        private Boolean isAgreedThird;

        public DetailDTO(CardInfo cardInfo) {
            this.cardInfoId = cardInfo.getId();
            this.cardNumber = cardInfo.getCardNumber();
            this.lastDigit = cardInfo.getLastDigit();
            this.expiryMonth = cardInfo.getExpiryMonth();
            this.cardOwner = cardInfo.getCardOwner();
            this.dateOfBirth = cardInfo.getDateOfBirth();
            this.isAgreedThird = cardInfo.getIsAgreedThird();
        }
    }

    //paymethod-manage
    @Data
    public static class paymethodManageDTO{
        Integer cardInfoId;
        String lastDigit;

        public paymethodManageDTO(CardInfo cardInfo) {
            this.cardInfoId = cardInfo.getId();
            this.lastDigit = cardInfo.getLastDigit();
        }
    }
}

