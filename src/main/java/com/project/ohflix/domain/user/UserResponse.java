package com.project.ohflix.domain.user;


import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.mylist.MyList;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

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

    // 유저 프로필 변경페이지 폼
    @Data
    public static class ProfileFormDTO {
        private Integer id;
        private ProfileIcon profileIcon;    // 프로필 아이콘
        private String username;            // 이름
        private String email;               // 이메일
        private Rate userSaveRate;          // 관람등급
        private Status isKids;              // 키즈 시청 제한 여부
        private Boolean isAutoPlay;         // 자동 재생 여부
        private Timestamp createdAt;
        private String profileIconPath;


        public ProfileFormDTO(User user) {
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

    // 맴버십 취소 페이지 폼 데이터
    @Data
    public static class CancelPlanPageDTO{
        private Integer userId;                 // 세션 유저 ID
        private Integer profile_icon_id;        // 프로필 아이콘 ID
        private String path;                    // 프로필 아이콘 경로
        private boolean isSubscribe;            // 구독 중인지, => 익섹셥 걸기
        private Timestamp oldestCreatedAt;  // 가장 오래된 createdAt
        private String latestServicePeriod;     // 가장 최근의 servicePeriod
//        private List<Content> contentList;      // 현재는 최신 컨텐츠 12개 뿌리기, 찜한 컨텐츠로 바꿀 수도

        public CancelPlanPageDTO(User user, PurchaseHistory oldestPurchaseHistory, PurchaseHistory latestPurchaseHistory) {
            this.userId = user.getId();
            this.profile_icon_id = user.getProfileIcon().getId();
            this.path = user.getProfileIcon().getPath();
            this.isSubscribe = user.getIsSubscribe();
            this.oldestCreatedAt = oldestPurchaseHistory != null ? oldestPurchaseHistory.getCreatedAt() : null;
            this.latestServicePeriod = latestPurchaseHistory != null ? latestPurchaseHistory.getServicePeriod() : null;
//            this.content = content;
        }
    }

}

