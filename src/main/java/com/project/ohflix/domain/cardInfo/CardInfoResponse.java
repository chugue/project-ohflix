package com.project.ohflix.domain.cardInfo;


import lombok.Data;

public class CardInfoResponse {

    //paymethod-manage
    @Data
    public static class CardNumber{
        String cardNumber;

        public CardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }
    }
}

