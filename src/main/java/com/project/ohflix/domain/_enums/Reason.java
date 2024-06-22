package com.project.ohflix.domain._enums;

import lombok.Getter;

/**
 * PG : 청소년
 * R : 성인
 * ALL : 전체 관람가
 */
@Getter
public enum Reason {

    SERVICE_QUALITY("서비스 품질 문제"),
    CONTENT_ISSUE("컨텐츠 부족 및 지역 제한"),
    TECHNICAL_ISSUE("버그 및 호환성 문제"),
    SUBSCRIPTION_ISSUE("자동 갱신 및 해지 잊음"),
    PAYMENT_ISSUE("중복 결제 및 과다 청구"),
    USER_EXPERIENCE("인터페이스, 알고리즘 불만족"),
    PERSONAL("개인적인 이유"),
    OTHER("과장 광고 및 정책 변경 불만");

    private final String value;

    Reason(String value) {
        this.value = value;
    }
}
