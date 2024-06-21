package com.project.ohflix.domain._enums;

import lombok.Getter;

/**
 * PG : 청소년
 * R : 성인
 * ALL : 전체 관람가
 */
@Getter
public enum Rate {

    PG("청소년 관람가"),
    R("청소년 관람 불가"),
    ALL("전체 관람가");

    private final String value;

    Rate(String value) {
        this.value = value;
    }
}
