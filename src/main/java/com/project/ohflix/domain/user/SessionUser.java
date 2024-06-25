package com.project.ohflix.domain.user;

import com.project.ohflix.domain._enums.Status;
import lombok.*;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionUser {

    private Integer id;
    private Status status; // 사용자? 관리자?

    @Builder
    public SessionUser(User user) {
        this.id = user.getId();
        this.status = user.getStatus();
    }
    @Builder
    public SessionUser() {

    }

}
