package com.project.ohflix.domain.purchaseHistory;


import com.project.ohflix.domain._enums.Paymethod;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PurchaseHistoryResponse {

    @Data
    public static class purchaseHistoryDTO {
        private String paymentDate;
        private List<purchase> purchaseDTO;

        public purchaseHistoryDTO(List<PurchaseHistory> purchaseHistories) {
            // Convert Timestamp to LocalDate
            LocalDate localDate = purchaseHistories.getFirst().getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // Add 30 days
            LocalDate newDate = localDate.plusDays(31);
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
            }
        }
    }
}

