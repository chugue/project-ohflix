package com.project.ohflix.domain.user;

import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
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
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String nickname; // 유저 닉네임, 유니크
    @Column(nullable = false)
    private String password;

    private String mobile; // 전화번호
    private String name; // 유저 이름

    @ManyToOne
    @ColumnDefault("1")
    private ProfileIcon profileIcon;// 프로필 아이콘

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    private Status status;          // USER(사용자) / ADMIN(관리자)

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ALL'")
    private Rate userSaveRate;      // 사용자가 설정한 관람등급

    @ColumnDefault("'FALSE'")
    private Boolean isKids;         // 키즈 등급 제한 여부
    @ColumnDefault("'FALSE'")
    private Boolean loginSave;      // 로그인 정보 저장 여부
    @ColumnDefault("'FALSE'")
    private Boolean isAutoPlay;     // 자동 재생 여부
    @ColumnDefault("'FALSE'")
    private Boolean isSubscribe;    // 구독/비구독 회원
    @ColumnDefault("")
    private String subscribeKey;    // 구독 회원일 경우 subsribeKey를 발급받음

    @CreationTimestamp
    private Timestamp createdAt;
    private String address;
    private String provider; // kakao, naver

    @Builder
    public User(Integer id, String email, String nickname, String password, String mobile, String name, ProfileIcon profileIcon, Status status, Rate userSaveRate, Boolean isKids, Boolean loginSave, Boolean isAutoPlay, Boolean isSubscribe, String subscribeKey, Timestamp createdAt, String address, String provider) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.mobile = mobile;
        this.name = name;
        this.profileIcon = profileIcon;
        this.status = status;
        this.userSaveRate = userSaveRate;
        this.isKids = isKids;
        this.loginSave = loginSave;
        this.isAutoPlay = isAutoPlay;
        this.isSubscribe = isSubscribe;
        this.subscribeKey = subscribeKey;
        this.createdAt = createdAt;
        this.address = address;
        this.provider = provider;
    }

    public void updatePassword (UserRequest.UpdatePasswordDTO reqDTO) {
        this.password = reqDTO.getNewPasswordCheck();
    }
}
