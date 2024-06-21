package com.project.ohflix.domain.content;

import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import com.project.ohflix.domain.purchaseHistory.PurchaseHistory;
import com.project.ohflix.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    // contentUpdateLinkPage
    Optional<Content> findById(@Param("id") Integer id);

    // videoManage
    List<Content> findAll();


    @Query("""
            SELECT c
            FROM Content c
            ORDER BY c.createdAt DESC
            """)
    List<Content> findLatestContent();

    List<Content> findALLByOrderByIdDesc();
}
