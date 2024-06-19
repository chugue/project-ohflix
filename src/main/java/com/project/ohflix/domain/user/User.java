package com.project.ohflix.domain.user;

import com.project.ohflix.domain._enums.Paymethod;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @OneToOne
    private ProfileIcon profileIcon; // 프로필 아이콘

    @Enumerated(EnumType.STRING)
    private Paymethod paymethod; // 카카오페이? 신용카드?

    @Enumerated(EnumType.STRING)
    private Status status; // 사용자? 관리자?

    private Boolean isKids; // 키즈 시청 제한 여부
    private Boolean loginSave; // 로그인 정보 저장 여부
    private Boolean isAutoPlay; // 자동 재생 여부
    private Boolean isSubscribe; // 유료회원? 구독회원?

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(Integer id, String email, String password, String username, ProfileIcon profileIcon, Paymethod paymethod, Status status, Boolean isKids, Boolean loginSave, Boolean isAutoPlay, Boolean isSubscribe, Timestamp createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.profileIcon = profileIcon;
        this.paymethod = paymethod;
        this.status = status;
        this.isKids = isKids;
        this.loginSave = loginSave;
        this.isAutoPlay = isAutoPlay;
        this.isSubscribe = isSubscribe;
        this.createdAt = createdAt;
    }
}
