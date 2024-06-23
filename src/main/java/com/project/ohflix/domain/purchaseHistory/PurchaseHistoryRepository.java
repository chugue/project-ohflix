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

    @Query("""
            select p from PurchaseHistory p join fetch p.user pu where pu.id = :userId and p.createdAt > :oneYearAgo
            """)
    Optional<List<PurchaseHistory>> findByUserId(@Param("userId") Integer userId, @Param("oneYearAgo") Timestamp oneYearAgo);

    @Query("""
            SELECT DISTINCT p
            FROM PurchaseHistory p
            JOIN FETCH p.user u
            WHERE u.id = :id
            """)
    List<PurchaseHistory> findByUser(@Param("id") int id);

    @Query("""
            SELECT p.createdAt
            FROM PurchaseHistory p
            JOIN p.user u
            WHERE u.id = :id
            ORDER BY p.createdAt ASC
            """)
    LocalDateTime findOldestCreatedAtByUserId(@Param("id") int id);

    @Query("""
            SELECT p.servicePeriod
            FROM PurchaseHistory p
            JOIN p.user u
            WHERE u.id = :id
            ORDER BY p.servicePeriod DESC
            """)
    String findLatestServicePeriodByUserId(@Param("id") int id);
}
