package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix.domain.cardInfo.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {

    Optional<PurchaseHistory> findFirstByUserIdOrderByCreatedAtDesc(Integer userId);

    @Query("""
            select p from PurchaseHistory p join fetch p.user pu where pu.id = :userId and p.createdAt > :oneYearAgo
            """)
    Optional<List<PurchaseHistory>> findByUserId(@Param("userId") Integer userId, @Param("oneYearAgo") Timestamp oneYearAgo);

    // PurchaseHistory id로 내림차순 ( 오래 된 결제 내역이 먼저 저장되게 )
    @Query("""
            SELECT DISTINCT ph
            FROM PurchaseHistory ph
            JOIN FETCH ph.user u
            JOIN FETCH u.profileIcon pi
            WHERE u.id = :id
            ORDER BY ph.id DESC
            """)
    List<PurchaseHistory> findByUser(@Param("id") int id);

    @Query("""
            select p from PurchaseHistory p
            Join fetch p.user u
            where p.user.id = :userId
            order by p.createdAt desc
            """)
    Optional<PurchaseHistory> findByUserIdWithRecentInfo(@Param("userId") Integer userId);
}
