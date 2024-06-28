package com.project.ohflix.domain.user;


import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.content.ContentResponse;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.profileIcon.ProfileIconResponse;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UserResponse {

    // 비밀번호 변경 페이지
    @Data
    public static class UpdatePasswordDTO {
        private String newPassword;


        public UpdatePasswordDTO(User user) {
            this.newPassword = user.getPassword();
        }
    }

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
    public static class MembersDTO {
        private Integer id;
        private String username;
        private Boolean isSubscribe;
        private Timestamp createdAt;
        private String formattedCreatedAt;
        private Integer monthsSubscribed;
        private Integer index;

        public MembersDTO(User user, int index) {
            this.id = user.getId();
            this.username = user.getNickname();
            this.isSubscribe = user.getIsSubscribe();
            this.createdAt = user.getCreatedAt();
            this.formattedCreatedAt = new SimpleDateFormat("MM-dd").format(user.getCreatedAt());
            this.monthsSubscribed = calculateMonthsSubscribed(user.getCreatedAt());
            this.index = index;
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
        private Boolean isKids;              // 키즈 시청 제한 여부
        private Boolean isAutoPlay;         // 자동 재생 여부
        private Timestamp createdAt;
        private String profileIconPath;


        public ProfileFormDTO(User user) {
            this.id = user.getId();
            this.username = user.getNickname();
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
        private String nickname;
        private Integer iconId;
        private String iconPath;

        public ProfileSettingDTO(User user) {
            this.userId = user.getId();
            this.nickname = user.getNickname();
            this.iconId = user.getProfileIcon().getId();
            this.iconPath = user.getProfileIcon().getPath();
        }
    }

    // 맴버십 취소 페이지 폼 데이터
    @Data
    public static class CancelPlanPageDTO {
        private Integer userId;                 // 세션 유저 ID
        private Integer profileIconId;          // 프로필 아이콘 ID
        private String profileIconPath;         // 프로필 아이콘 경로
        private boolean isSubscribe;            // 구독 중인지, => 익섹셥 걸기
        private String oldestServicePeriod;     // 가장 오래된 servicePeriod
        //        private Timestamp oldestCreatedAt;     // 가장 오래된 createdAt
        private String latestServicePeriod;     // 가장 최근의 servicePeriod
        private String oldestPurchaseHistory;     // 가장 최근의 servicePeriod
        private List<ContentResponse.CancelPlanPageContentDTO> latestContentList; // 현재는 최신 컨텐츠 12개 뿌리기, 찜한 컨텐츠로 바꿀 수도

        // ~ 뒤의 날짜를 추출
        private String endDate(String servicePeriod) {
            if (servicePeriod != null && servicePeriod.contains("~")) {
                return servicePeriod.split("~")[1];
            }
            return servicePeriod;
        }

        // ~ 앞의 날짜를 추출
        private String startDate(String servicePeriod) {
            if (servicePeriod != null && servicePeriod.contains("~")) {
                return servicePeriod.split("~")[0].trim();
            }
            return servicePeriod;
        }
//        // Timestamp에서 연월일을 추출하는 메서드
//        private String formatDate(Timestamp timestamp) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            return dateFormat.format(timestamp);
//        }

        public CancelPlanPageDTO(User user, PurchaseHistory oldestPurchaseHistory, PurchaseHistory latestPurchaseHistory, List<Content> latestContentList) {
            this.userId = user.getId();
            this.profileIconId = user.getProfileIcon().getId();
            this.profileIconPath = user.getProfileIcon().getPath();
            this.isSubscribe = user.getIsSubscribe();
//            this.oldestCreatedAt = formatDate(oldestPurchaseHistory != null ? oldestPurchaseHistory.getCreatedAt() : null);
//            this.oldestCreatedAt = oldestPurchaseHistory != null ? oldestPurchaseHistory.getCreatedAt() : null;
            this.oldestServicePeriod = startDate(oldestPurchaseHistory != null ? oldestPurchaseHistory.getServicePeriod() : null);
            this.latestServicePeriod = endDate(latestPurchaseHistory != null ? latestPurchaseHistory.getServicePeriod() : null);
            this.latestContentList = latestContentList.stream()
                    .map(ContentResponse.CancelPlanPageContentDTO::new)
                    .toList();
        }
    }


    // sales-page
    @Data
    public static class SalesPageUserDTO {
        private String yearMonth;
        private Long monthlyUserCount;
        private Long cumulativeUserCount;

        public SalesPageUserDTO(String yearMonth, Long monthlyUserCount, Long cumulativeUserCount) {
            this.yearMonth = yearMonth;
            this.monthlyUserCount = monthlyUserCount;
            this.cumulativeUserCount = cumulativeUserCount;
        }
    }

    // sales-page
    @Data
    public static class SalesPageSubscribeUserDTO {
        private String yearMonth;
        private Long subscribeUserCount;

        public SalesPageSubscribeUserDTO(String yearMonth, Long subscribeUserCount) {
            this.yearMonth = yearMonth;
            this.subscribeUserCount = subscribeUserCount;
        }

    }

    // sales-page
    @Data
    public static class SalesPageDTO {
        private String yearMonth;
        private Long subscribeUserCount;
        private Long cumulativeUserCount;
        private Long monthlySales;
        private Long cumulativeSales;

        public SalesPageDTO(String yearMonth, Long subscribeUserCount, Long cumulativeUserCount, Long monthlySales, Long cumulativeSales) {
            this.yearMonth = yearMonth;
            this.subscribeUserCount = subscribeUserCount;
            this.cumulativeUserCount = cumulativeUserCount;
            this.monthlySales = monthlySales;
            this.cumulativeSales = cumulativeSales;
        }
    }

    // 멤버십 상세정보 페이지 DTO
    @Data
    public static class AccountMembershipDTO {
        private Integer userId;                 // 세션 유저 ID
        private Integer profileIconId;          // 프로필 아이콘 ID
        private String profileIconPath;         // 프로필 아이콘 경로 ▶ profile_icon
        private String servicePeriod;           // 다음 결제일 ▶ purchase_history
        private String lastDigit;              // 카드 끝 4자리 ▶ card_info

        // ~ 뒤의 날짜를 추출
        private String extractEndDate(String servicePeriod) {
            if (servicePeriod != null && servicePeriod.contains("~")) {
                return servicePeriod.split("~")[1];
            }
            return servicePeriod;
        }

        public AccountMembershipDTO(User user, PurchaseHistory purchaseHistory, CardInfo cardInfo) {
            this.userId = user.getId();
            this.profileIconId = user.getProfileIcon().getId();
            this.profileIconPath = user.getProfileIcon().getPath();
            this.servicePeriod = extractEndDate(purchaseHistory.getServicePeriod());
            this.lastDigit = cardInfo.getLastDigit();
        }

    }


    // 멤버십 기본정보 페이지 DTO
    @Data
    public static class AccountMembershipInfoDTO {
        private Integer userId;
        private Integer profileIconId;
        private String profileIconPath;
        private String membershipStartDate;
        private String membershipType;
        private String nextPaymentDate;
        private String cardLastFourDigits;

        public AccountMembershipInfoDTO(User user, PurchaseHistory purchaseHistory, CardInfo cardInfo) {
            this.userId = user.getId();
            this.profileIconId = user.getProfileIcon().getId();
            this.profileIconPath = user.getProfileIcon().getPath();
            this.membershipStartDate = user.getCreatedAt().toString(); // 예시 데이터
            this.membershipType = user.getIsSubscribe() ? "스탠다드" : "구독 안됨";
            this.nextPaymentDate = extractEndDate(purchaseHistory.getServicePeriod());
            this.cardLastFourDigits = cardInfo.getLastDigit();
        }

        private String extractEndDate(String servicePeriod) {
            if (servicePeriod != null && servicePeriod.contains("~")) {
                return servicePeriod.split("~")[1];
            }
            return servicePeriod;
        }
    }

    //login
    @Data
    public static class LoginDTO {
        private Integer Id;
        private Status status;

        public LoginDTO(Integer id, Status status) {
            Id = id;
            this.status = status;
        }
    }

    // 회원가입 응답 DTO
    @Data
    public static class SignupDTO{
        // 회원가입 입력 값.
        private String email;
        private String nickname;
        private String password;
        // Default 값
        private Status status;          // USER(사용자) / ADMIN(관리자)

        public SignupDTO(User user) {
            this.email = user.getEmail();
            this.password = user.getPassword();
            this.nickname = user.getNickname();
            this.status = user.getStatus();
        }
    }
}

