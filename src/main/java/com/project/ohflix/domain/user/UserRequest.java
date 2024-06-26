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

    // 회원가입 요청 DTO
    @Data
    public static class SignupDTO {
        // 회원가입 입력 값.
        private String email;
        private String password;
        private String nickname;
        // Default 값
        private Status status;      // USER(사용자)/ADMIN(관리자)

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .status(status)
                    .build();
        }
    }
}
