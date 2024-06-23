package com.project.ohflix.domain.cardInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardInfoRepository extends JpaRepository<CardInfo, Integer> {


    @Query(""" 
             select c from CardInfo c join fetch c.user u where c.user.id = :userId and c.isMain = true
             """) // user-check 페이지에서 사용하는 쿼리
    Optional<CardInfo> findUserInfo(@Param("userId") Integer userId);

    @Query("""
            select c from CardInfo c join fetch c.user cu where cu.id = :userId
            """)
    Optional<List<CardInfo>> findByUserId(@Param("userId") Integer userId);

    @Query("""
            select c from CardInfo c where c.id = :cardInfoId 
            """)
    Optional<CardInfo> findCardInfoById(@Param("cardInfoId") Integer cardInfoId);
}
