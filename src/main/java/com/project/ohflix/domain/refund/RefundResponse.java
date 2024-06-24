package com.project.ohflix.domain.refund;


import com.project.ohflix.domain._enums.Refuse;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RefundResponse {

    @Data
    public static class ListDTO {
        private List<RefundInfo> refundInfos = new ArrayList<>();

        public ListDTO(List<Refund> refunds) {
            this.refundInfos = IntStream.range(0, refunds.size())
                    .mapToObj(i -> new RefundInfo(i + 1, refunds.get(i)))
                    .collect(Collectors.toList());
        }

        @Data
        public class RefundInfo {
            private Integer num;
            private String username;
            private String email;
            private String reason;
            private String purchasedDate;
            private Boolean isApproved;

            public RefundInfo(Integer num, Refund refund) {
                this.num = num;
                this.username = refund.getUser().getUsername();
                this.email = refund.getUser().getEmail();
                this.reason = refund.getReason().getTitle();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                this.purchasedDate = formatter.format(refund.getPurchasedDate());
                if (refund.getStatus().equals(Refuse.PENDING)){
                    this.isApproved = false;
                } else if (refund.getStatus().equals(Refuse.ACCEPTED)){
                    this.isApproved = true;
                } else {
                    this.isApproved = false;
                }
            }
        }
    }

}
