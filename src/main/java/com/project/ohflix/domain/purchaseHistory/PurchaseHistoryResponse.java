package com.project.ohflix.domain.purchaseHistory;


import com.project.ohflix.domain.user.User;
import lombok.Data;

public class PurchaseHistoryResponse {

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
}


