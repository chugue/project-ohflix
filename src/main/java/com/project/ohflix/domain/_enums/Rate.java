package com.project.ohflix.domain._enums;

import lombok.Getter;

/**
 * PG : 청소년
 * R : 성인
 * ALL : 전체 관람가
 */
@Getter
public enum Rate {

    PG("15세 이하 관람 불가", "/static/images/system/rate_level/15.png"),
    R("19세 이하 관람 불가" , "/static/images/system/rate_level/r.png"),
    ALL("전체 관람가", "/static/images/system/rate_level/all.png");

    private final String value;
    private final String imgPath;

    Rate(String value, String imgPath) {
        this.value = value;
        this.imgPath = imgPath;
    }
}
