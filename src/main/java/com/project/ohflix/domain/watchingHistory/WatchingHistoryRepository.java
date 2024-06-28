package com.project.ohflix.domain.watchingHistory;

import com.project.ohflix.domain.mylist.MyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface WatchingHistoryRepository extends JpaRepository<WatchingHistory, Integer> {
    @Query("""
            SELECT  w
            FROM WatchingHistory w
            JOIN FETCH w.user wu
            JOIN FETCH wu.profileIcon wup
            JOIN FETCH w.content wc
            WHERE wu.id = :userId
            """)
    List<WatchingHistory> findByUserId(@Param("userId") Integer userId);
}
