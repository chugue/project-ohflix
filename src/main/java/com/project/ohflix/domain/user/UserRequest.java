package com.project.ohflix.domain.user;

import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

public class UserRequest {

    // 비밀번호 변경 페이지
    @Data
    public static class UpdatePasswordDTO {

        private String currentPassword;

        @NotBlank(message = "필수 항목입니다.")
        @Size(min = 6, message = "비밀번호는 6자 이상이어야 합니다.")
        @Pattern(regexp = "^[^\\s]+$", message = "비밀번호는 공백을 포함할 수 없습니다.") // 공백 제외
        private String newPassword;

        private String newPasswordCheck;
    }

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
