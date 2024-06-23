package com.project.ohflix.domain.cardInfo;


import lombok.Data;

public class CardInfoResponse {

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

