package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix.domain._enums.Paymethod;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "purchase_history_tb")
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "card_info_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CardInfo cardInfo; // 카드 정보

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 사용자

    @Column( nullable = false)
    private String description; // 설명 : 스트리밍 서비스

    @Column(nullable = false)
    private String servicePeriod; // 서비스 기간

    @Enumerated(EnumType.STRING)
    private Paymethod paymethod; // 결제 방법 (카카오 페이 / 신용카드 )

    private Integer amount; // 결제 금액

    private Timestamp createdAt; // 결제일

    @Builder
    public PurchaseHistory(Integer id, CardInfo cardInfo, User user, String description, String servicePeriod, Paymethod paymethod, Integer amount, Timestamp createdAt) {
        this.id = id;
        this.cardInfo = cardInfo;
        this.user = user;
        this.description = description;
        this.servicePeriod = servicePeriod;
        this.paymethod = paymethod;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}
