package com.project.ohflix.domain.user;

import com.project.ohflix.domain._enums.Status;
import lombok.Builder;
import lombok.Data;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionUser {

    private Integer id;
    private Status status; // 사용자? 관리자?

    @Builder
    public SessionUser(Integer id, Status status) {
        this.id = id;
        this.status = status;
    }
    @Builder
    public SessionUser() {

    }

}
