package com.project.ohflix.domain.like;

import com.project.ohflix.domain.mylist.MyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    @Query("""
            SELECT l
            FROM Like l
            WHERE l.user.id = :userId AND l.content.id = :contentId
            """)
    Optional<Like> findByUserIdAndContentId(@Param("userId") int userId, @Param("contentId") int contentId);
}
