package com.project.ohflix.domain.user;

import com.project.ohflix.domain._enums.Paymethod;
import com.project.ohflix.domain._enums.Rate;
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

    private String mobile; // 전화번호

    @Column(nullable = false)
    private String username; // 유저 아이디

    @Column(nullable = false)
    private String name; // 유저 성리

    @OneToOne
    private ProfileIcon profileIcon; // 프로필 아이콘

    @Enumerated(EnumType.STRING)
    private Status status; // 사용자? 관리자?

    @Enumerated(EnumType.STRING)
    private Rate userSaveRate; // 사용자가 설정한 관람등급

    private Status isKids; // 키즈 시청 제한 여부
    private Boolean loginSave; // 로그인 정보 저장 여부
    private Boolean isAutoPlay; // 자동 재생 여부
    private Boolean isSubscribe; // 유료회원? 구독회원?

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(Integer id, String email, String password, String mobile, String username, String name, ProfileIcon profileIcon, Status status, Rate userSaveRate, Status isKids, Boolean loginSave, Boolean isAutoPlay, Boolean isSubscribe, Timestamp createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.username = username;
        this.name = name;
        this.profileIcon = profileIcon;
        this.status = status;
        this.userSaveRate = userSaveRate;
        this.isKids = isKids;
        this.loginSave = loginSave;
        this.isAutoPlay = isAutoPlay;
        this.isSubscribe = isSubscribe;
        this.createdAt = createdAt;
    }

    
}
