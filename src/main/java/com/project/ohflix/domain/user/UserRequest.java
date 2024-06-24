package com.project.ohflix.domain.user;

import lombok.Data;

public class UserRequest {

    //login
    @Data
    public static class LoginDTO{
        private String email;
        private String password;

    }
}
