package com.project.ohflix.domain.user;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    // login - page
    @Data
    public static class LoginDTO{
        @NotBlank(message = "아이디는 필수 항목입니다.")
        private String email;


        @NotBlank(message = "비밀번호는 필수 항목입니다.")
        private String password; // TODO: 암호화

    }
}
