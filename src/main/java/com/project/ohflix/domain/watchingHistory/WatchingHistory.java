package com.project.ohflix.domain.watchingHistory;

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
@Table(name = "watching_history_tb")
public class WatchingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "content_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Content content;

    private Double playedTime;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public WatchingHistory(Integer id, User user, Content content, Double playedTime, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.playedTime = playedTime;
        this.createdAt = createdAt;
    }

}