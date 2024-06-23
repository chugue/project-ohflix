package com.project.ohflix.domain.refund;

import com.project.ohflix.domain._enums.Paymethod;
import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain._enums.Reason;
import com.project.ohflix.domain._enums.Refuse;
import com.project.ohflix.domain.cardInfo.CardInfo;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import com.project.ohflix.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "refund_tb")
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @JoinColumn(name = "user_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user; // 사용자

    @Enumerated(EnumType.STRING)
    private Reason reason; // 환불 요청 사유

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PENDING'")
    private Refuse status; // 환불 상태 (디폴트 값 '대기중')
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private PurchaseHistory purchaseHistory;

    @Builder
    public Refund(Integer id, Reason reason, Refuse status, PurchaseHistory purchaseHistory) {
        this.id = id;
        this.reason = reason;
        this.status = status;
        this.purchaseHistory = purchaseHistory;
    }
}
