package com.project.ohflix.domain.like;

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
@Table(name = "like_tb")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "content_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Content content;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Like(Integer id, User user, Content content, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
    }
}