package com.project.ohflix.domain.purchaseHistory;


import com.project.ohflix.domain._enums.Genre;
import com.project.ohflix.domain._enums.Paymethod;
import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain.content.Content;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.project.ohflix.domain.user.User;
import lombok.Data;

public class PurchaseHistoryResponse {

    //purchase-histories
    @Data
    public static class purchaseHistoryDTO {
        private String paymentDate;
        private List<purchase> purchaseDTO;

        public purchaseHistoryDTO(List<PurchaseHistory> purchaseHistories) {
            // Convert Timestamp to LocalDate
            LocalDate localDate = purchaseHistories.getFirst().getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // Add 30 days
            LocalDate newDate = localDate.plusDays(30);
            // Format the new date in "yyyy년 MM월 dd일" format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
            this.paymentDate = newDate.format(formatter);
            this.purchaseDTO = purchaseHistories.stream().map(purchaseHistory -> new purchase(purchaseHistory)).toList();
        }

        @Data
        public static class purchase {
            private int id;
            private String createdAt;
            private String description;
            private String servicePeriod;
            private Paymethod paymethod;
            private String cardNumber;
            private Integer amount;
            private Integer vat;
            private Integer supplyValue;

            public purchase(PurchaseHistory purchaseHistory) {
                this.id = purchaseHistory.getId();
                // Convert Timestamp to LocalDate
                LocalDate localDate = purchaseHistory.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                // Format the new date in "yyyy년 MM월 dd일" format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
                this.createdAt = localDate.format(formatter);
                this.description = purchaseHistory.getDescription();
                this.servicePeriod = purchaseHistory.getServicePeriod();
                this.paymethod = purchaseHistory.getPaymethod();
                this.cardNumber = purchaseHistory.getCardInfo().getCardNumber();
                this.amount = purchaseHistory.getAmount();
                this.vat = (int) (purchaseHistory.getAmount()*0.1);
                this.supplyValue = purchaseHistory.getAmount()-vat;
            }
        }
    }

    // account-security 페이지
    @Data
    public static class AccountSecurityDTO {
        private Integer id; // userId
        private String email;
        private String mobile;


        public AccountSecurityDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.mobile = user.getMobile();
        }
    }

    // sales-page
    @Data
    public static class SalesPageSalesDTO {
        private String yearMonth;
        private Long monthlySales;

        public SalesPageSalesDTO(String yearMonth, Long monthlySales) {
            this.yearMonth = yearMonth;
            this.monthlySales = monthlySales;
        }

    }

    // 결제 준비 DTO
    @Data
    public static class KakaoPayReadyDTO{

        private String tid;
        private String nextRedirectAppUrl;
        private String nextRedirectMobileUrl;
        private String nextRedirectPcUrl;
        private String androidAppScheme;
        private String iosAppScheme;
        private Timestamp createdAt;


        public KakaoPayReadyDTO(String tid, String nextRedirectAppUrl, String nextRedirectMobileUrl, String nextRedirectPcUrl, String androidAppScheme, String iosAppScheme, Timestamp createdAt) {
            this.tid = tid;
            this.nextRedirectAppUrl = nextRedirectAppUrl;
            this.nextRedirectMobileUrl = nextRedirectMobileUrl;
            this.nextRedirectPcUrl = nextRedirectPcUrl;
            this.androidAppScheme = androidAppScheme;
            this.iosAppScheme = iosAppScheme;
            this.createdAt = createdAt;
        }


    }

    // 카카오페이 결제 승인 응답 DTO
    @Data
    public static class KakaoPayApproveDTO{
        private String aid;
        private String tid;
        private String cid;
        private String sid;
        private String partnerOrderId;
        private String partnerUserId;
        private String paymentMethodType;
        private Amount amount;
        private PaymentCardInfo cardInfo;
        private String itemName;
        private String itemCode;
        private Integer quantity;
        private Timestamp createdAt;
        private Timestamp approvedAt;

        public KakaoPayApproveDTO(String aid, String tid, String cid, String sid, String partnerOrderId, String partnerUserId, String paymentMethodType, Amount amount, PaymentCardInfo cardInfo, String itemName, String itemCode, Integer quantity, Timestamp createdAt, Timestamp approvedAt) {
            this.aid = aid;
            this.tid = tid;
            this.cid = cid;
            this.sid = sid;
            this.partnerOrderId = partnerOrderId;
            this.partnerUserId = partnerUserId;
            this.paymentMethodType = paymentMethodType;
            this.amount = amount;
            this.cardInfo = cardInfo;
            this.itemName = itemName;
            this.itemCode = itemCode;
            this.quantity = quantity;
            this.createdAt = createdAt;
            this.approvedAt = approvedAt;
        }

        @Data
        public static class Amount {
            private Integer total;
            private Integer taxFree;
            private Integer vat;
            private Integer point;
            private Integer discount;
            private Integer greenDeposit;

            public Amount(Integer total, Integer taxFree, Integer vat, Integer point, Integer discount, Integer greenDeposit) {
                this.total = total;
                this.taxFree = taxFree;
                this.vat = vat;
                this.point = point;
                this.discount = discount;
                this.greenDeposit = greenDeposit;
            }
        }

        @Data
        public static class PaymentCardInfo {
            private String kakaopayPurchaseCorp;
            private String kakaopayPurchaseCorpCode;
            private String kakaopayIssuerCorp;
            private String kakaopayIssuerCorpCode;
            private String bin;
            private String cardType;
            private String installMonth;
            private String approvedId;
            private String cardMid;
            private String interestFreeInstall;
            private String installmentType;
            private String cardItemCode;

            public PaymentCardInfo(String kakaopayPurchaseCorp, String kakaopayPurchaseCorpCode, String kakaopayIssuerCorp, String kakaopayIssuerCorpCode, String bin, String cardType, String installMonth, String approvedId, String cardMid, String interestFreeInstall, String installmentType, String cardItemCode) {
                this.kakaopayPurchaseCorp = kakaopayPurchaseCorp;
                this.kakaopayPurchaseCorpCode = kakaopayPurchaseCorpCode;
                this.kakaopayIssuerCorp = kakaopayIssuerCorp;
                this.kakaopayIssuerCorpCode = kakaopayIssuerCorpCode;
                this.bin = bin;
                this.cardType = cardType;
                this.installMonth = installMonth;
                this.approvedId = approvedId;
                this.cardMid = cardMid;
                this.interestFreeInstall = interestFreeInstall;
                this.installmentType = installmentType;
                this.cardItemCode = cardItemCode;
            }
        }
    }





}

