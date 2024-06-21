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

    private String playedTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "watch_or_fav")
    private WatchOrFav watchOrFav; // Enum으로 선언

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