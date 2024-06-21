package com.project.ohflix.domain.user;


import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

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

    // user profile form 페이지
    @Data
    public static class UserProfileFormDTO {
        private Integer id;
        private ProfileIcon profileIcon;    // 프로필 아이콘
        private String username;            // 이름
        private String email;               // 이메일
        private Rate userSaveRate;          // 관람등급
        private Status isKids;              // 키즈 시청 제한 여부
        private Boolean isAutoPlay;         // 자동 재생 여부
        private Timestamp createdAt;

        private String profileIconPath;

        public UserProfileFormDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.profileIcon = user.getProfileIcon();
            this.userSaveRate = user.getUserSaveRate();
            this.isKids = user.getIsKids();
            this.isAutoPlay = user.getIsAutoPlay();
            this.createdAt = user.getCreatedAt();

            this.profileIconPath = user.getProfileIcon().getPath();
        }
    }
}

