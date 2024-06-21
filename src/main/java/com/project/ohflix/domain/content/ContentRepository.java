package com.project.ohflix.domain.content;

import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    @Query("""
            SELECT c
            FROM Content c
            ORDER BY c.createdAt DESC
            """)
    List<Content> findLatestContent();

    List<Content> findALLByOrderByIdDesc();
}
