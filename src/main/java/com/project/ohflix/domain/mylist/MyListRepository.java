package com.project.ohflix.domain.mylist;

import org.springframework.data.jpa.repository.JpaRepository;
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
            WHERE u.id = :id
            """)
    List<MyList> findMyListByUserId(@Param("id") int id);

    @Query("""
            SELECT m
            FROM MyList m
            WHERE m.user.id = :userId AND m.content.id = :contentId AND m.watchOrFav = 'FAVORITE'
            """)
    Optional<MyList>findByUserIdAndContentId(@Param("userId") int userId, @Param("contentId") int contentId);
}
