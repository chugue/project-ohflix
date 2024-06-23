package com.project.ohflix.domain.cardInfo;

import com.project.ohflix.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@Entity
@Data
@Table(name = "card_info_tb")
public class CardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String cardNumber; // 카드 번호

    @Column(nullable = false)
    private String lastDigit; // 카드 번호 끝 4자리

    @Column(nullable = false)
    private String expiryMonth; // 유효 기간

    @Column(nullable = false)
    private String cardOwner; // 카드 소유자 이름

    @Column(nullable = false)
    private LocalDate dateOfBirth; // 카드 소유자 생일

    private Boolean isMain; // 실제 결제 사용 카드 여부?

    private Boolean isAgreedThird; // 제 3자 정보이용 동의

    @Builder
    public CardInfo(Integer id, User user, String cardNumber, String lastDigit, String expiryMonth, String cardOwner, LocalDate dateOfBirth, Boolean isMain, Boolean isAgreedThird) {
        this.id = id;
        this.user = user;
        this.cardNumber = cardNumber;
        this.lastDigit = lastDigit;
        this.expiryMonth = expiryMonth;
        this.cardOwner = cardOwner;
        this.dateOfBirth = dateOfBirth;
        this.isMain = isMain;
        this.isAgreedThird = isAgreedThird;
    }
}
