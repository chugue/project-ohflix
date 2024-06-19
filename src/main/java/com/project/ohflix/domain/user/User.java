package com.project.ohflix.domain.user;

import com.project.ohflix.domain._enums.Paymethod;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private ProfileIcon profileIcon;

    @Enumerated(EnumType.STRING)
    private Paymethod paymethod;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Boolean isKids;
    private Boolean loginSave;
    private Boolean isAutoPlay;
    private Boolean isSubscribe;

    @Builder
    public User(Integer id, String email, String password, String username, Paymethod paymethod, Status status, Boolean isKids, Boolean loginSave, Boolean isAutoPlay, Boolean isSubscribe) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.paymethod = paymethod;
        this.status = status;
        this.isKids = isKids;
        this.loginSave = loginSave;
        this.isAutoPlay = isAutoPlay;
        this.isSubscribe = isSubscribe;
    }
}
