package com.project.ohflix.domain.user;


import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain.cardInfo.CardInfo;
import lombok.Data;

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

    //profile-setting
    @Data
    public static class ProfileSettingDTO {
        private Integer userId;
        private String username;
        private Integer iconId;
        private String iconPath;

        public ProfileSettingDTO(User user) {
            this.userId = user.getId();
            this.username = user.getUsername();
            this.iconId = user.getProfileIcon().getId();
            this.iconPath = user.getProfileIcon().getPath();
        }
    }

}

