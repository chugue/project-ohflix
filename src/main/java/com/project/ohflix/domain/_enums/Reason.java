package com.project.ohflix.domain._enums;

import lombok.Getter;

/**
 * PG : 청소년
 * R : 성인
 * ALL : 전체 관람가
 */
@Getter
public enum Reason {

    SERVICE_QUALITY("service-quality", "스트리밍 중 버퍼링이나 화질 저하 등 품질 문제.", "품질 문제"),
    CONTENT_ISSUE("content-issue", "기대했던 콘텐츠 부족 또는 지역 제한으로 인한 접근 불가.","컨텐츠 부족"),
    TECHNICAL_ISSUE("technical-issue", "앱이나 웹사이트의 버그 및 디바이스 호환성 문제.", "호환성 문제"),
    SUBSCRIPTION_ISSUE("subscription-issue", "자동 갱신 실수 또는 해지 요청을 잊은 경우.", "자동 갱신 실수"),
    PAYMENT_ISSUE("payment-issue", "중복 결제나 과다 청구 등 결제 관련 오류.","중복 결제" ),
    USER_EXPERIENCE("user-experience", "인터페이스 불편함이나 추천 알고리즘 부정확성.", "불편함 문제"),
    PERSONAL_REASON("personal-reason", "경제적 이유나 개인적인 서비스 이용 중단 결정.", "경제적 이유"),
    OTHER("other", "과장된 광고나 정책 변경으로 인한 불만.", "과장된 광고");

    private final String value;
    private final String explanation;
    private final String title;

    Reason(String value, String explanation, String title) {
        this.value = value;
        this.explanation = explanation;
        this.title = title;
    }

    public static Reason fromString(String text) {
        for (Reason r : Reason.values()) {
            if (r.value.equals(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
