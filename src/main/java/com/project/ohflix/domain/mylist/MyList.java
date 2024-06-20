package com.project.ohflix.domain.mylist;

import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "mylist_tb")
public class MyList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "content_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Content content;

    private String playedTime; // 이어보기 재생 시간 - 시청중인 컨텐츠 보기에서 사용

    @Enumerated(EnumType.STRING)
    private WatchOrFav watchOrFav; // 시청중인 컨텐츠인지, 찜한 컨텐츠인지
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public MyList(Integer id, User user, Content content, String playedTime, WatchOrFav watchOrFav, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.playedTime = playedTime;
        this.watchOrFav = watchOrFav;
        this.createdAt = createdAt;
    }
}
