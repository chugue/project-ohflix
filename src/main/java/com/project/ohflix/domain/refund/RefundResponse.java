package com.project.ohflix.domain.refund;

import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RefundResponse {

    // refund-page 페이지
    @Data
    public static class RefundPageDTO{
        private List<RefundPage> refundPageDTO;

        public RefundPageDTO(List<Refund> refunds){
            this.refundPageDTO = refunds.stream().map(refundPage -> new RefundPage(refundPage)).toList();
        }

        @Data
        public static class RefundPage{
            private int id;
            private String name;
            private String username;
            private String reason;
            private String status;
            //private String createdAt;

            public RefundPage(Refund refund) {
                this.id = refund.getId();
                this.name = refund.getUser().getName();
                this.username = refund.getUser().getUsername();
                this.reason = refund.getReason().getValue();
                this.status = refund.getStatus().getValue();
//                if (refund.getPurchaseHistory().getCreatedAt() != null) {
//                    // Convert Timestamp to LocalDate
//                    LocalDate localDate = refund.getPurchaseHistory().getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                    // Format the new date in "yyyy년 MM월 dd일" format
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
//                    this.createdAt = localDate.format(formatter);
//                }
            }
        }


    }

}
