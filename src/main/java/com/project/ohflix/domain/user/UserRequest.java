package com.project.ohflix.domain.user;

import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

public class UserRequest {

    // login - page
    @Data
    public static class LoginDTO {
        @NotBlank(message = "아이디는 필수 항목입니다.")
        private String email;


        @NotBlank(message = "비밀번호는 필수 항목입니다.")
        private String password; // TODO: 암호화

    }

    // 회원가입 DTO
    @Data
    public static class SignupDTO {
        // 회원가입 입력 값.
        private String email;
        private String password;
        private String nickname;
        private String name;
        private String mobile;

        private ProfileIcon profileIcon;// 프로필 아이콘
        private Status status;          // USER(사용자) / ADMIN(관리자)
        private Rate userSaveRate;      // 사용자가 설정한 관람등급
        private Boolean isKids;         // 키즈 등급 제한 여부
        private Boolean loginSave;      // 로그인 정보 저장 여부
        private Boolean isAutoPlay;     // 자동 재생 여부
        private Boolean isSubscribe;    // 구독/비구독 회원
        private Timestamp createdAt;

        //         왜 있는 값이지?
//        private String address;
//        private String provider;


        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .name(name)
                    .mobile(mobile)
                    .profileIcon(ProfileIcon.builder().id(1).build())
                    .status(Status.USER)
                    .userSaveRate(Rate.ALL)
                    .isKids(false)
                    .loginSave(false)
                    .isAutoPlay(false)
                    .isSubscribe(false)
                    .createdAt(createdAt)
                    .build();
        }
    }
}
