package com.project.ohflix.domain.user;


import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain.cardInfo.CardInfo;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;

public class UserResponse {

    // 로그인 사용자의 관람등급 가져오기
    @Data
    public static class RestrictionLevelDTO {
        private Rate userSaveRate;

        public RestrictionLevelDTO(User user) {
            this.userSaveRate = user.getUserSaveRate();
        }
    }

    // user-check 페이지
    @Data
    public static class UserCheckDTO {
        private String mobile;
        private String email;
        private String lastDigit;

        public UserCheckDTO(CardInfo cardInfo) {
            this.mobile = cardInfo.getUser().getMobile();
            this.email = cardInfo.getUser().getEmail();
            this.lastDigit = cardInfo.getLastDigit();
        }
    }

    // admin/member-manage 페이지

    @Data
    public static class MembersDTO{
        private Integer id;
        private String username;
        private Boolean isSubscribe;
        private Timestamp createdAt;
        private Integer monthsSubscribed;

        public MembersDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.isSubscribe = user.getIsSubscribe();
            this.createdAt = user.getCreatedAt();
            this.monthsSubscribed = calculateMonthsSubscribed(user.getCreatedAt());
        }

        private Integer calculateMonthsSubscribed(Timestamp createdAt) {
            LocalDate createdDate = createdAt.toLocalDateTime().toLocalDate();
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(createdDate, currentDate);
            return period.getYears() * 12 + period.getMonths();
        }
    }


}

