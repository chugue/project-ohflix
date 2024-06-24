package com.project.ohflix.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest {

    //login
    @Data
    public class LoginDTO{
        private String email;
        private String password;
    }
}
