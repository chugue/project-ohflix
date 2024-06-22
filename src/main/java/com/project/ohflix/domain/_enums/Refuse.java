package com.project.ohflix.domain._enums;

import lombok.Getter;

@Getter
public enum Refuse {

    PENDING("대기중"),
    ACCEPTED("수락"),
    REJECTED("반려");

    private final String value;

    Refuse(String value) {
        this.value = value;
    }
}
