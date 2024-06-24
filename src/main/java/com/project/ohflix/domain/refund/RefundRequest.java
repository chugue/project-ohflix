package com.project.ohflix.domain.refund;

import com.project.ohflix.domain._enums.Reason;
import lombok.Data;

public class RefundRequest {

    @Data
    public static class RequestDTO {
        private Reason refundReason;
        private Integer userId;

        public RequestDTO(String refundReason, Integer sessionUserId) {
            System.out.println("🔹🔹🔹🔹🔹🔹" + refundReason);
            this.refundReason = Reason.fromString(refundReason);
            this.userId = sessionUserId;
        }
    }
}
