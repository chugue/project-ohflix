package com.project.ohflix.domain.mylist;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface MyListRepository extends JpaRepository<MyList, Integer> {
    @Query("""
            SELECT DISTINCT m
            FROM MyList m
            JOIN FETCH m.user u
            JOIN FETCH m.content c
            WHERE u.id = :id AND m.watchOrFav = 'FAVORITE'
            """)
    List<MyList> findMyListByUserId(@Param("id") int id);

    @Query("""
            SELECT DISTINCT m
            FROM MyList m
            JOIN FETCH m.user u
            JOIN FETCH m.content c
            WHERE u.id = :id AND m.watchOrFav = 'WATCHING'
            """)
    List<MyList> findMyWatchListByUserId(@Param("id") int id);

    @Query("""
            SELECT m
            FROM MyList m
            WHERE m.user.id = :userId AND m.content.id = :contentId AND m.watchOrFav = 'FAVORITE'
            """)
    Optional<MyList>findByUserIdAndContentId(@Param("userId") int userId, @Param("contentId") int contentId);

    @Query("""
            SELECT m
            FROM MyList m
            WHERE m.user.id = :userId AND m.content.id = :contentId AND m.watchOrFav = 'WATCHING'
            """)
    Optional<MyList> findMyListByUserIdAndContentIdAndWatch(@Param("userId") int userId, @Param("contentId") int contentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM MyList m WHERE m.user.id = :userId AND m.content.id = :contentId")
    void deleteByUserIdAndContentId(@Param("userId") Integer userId, @Param("contentId") Integer contentId);
}
