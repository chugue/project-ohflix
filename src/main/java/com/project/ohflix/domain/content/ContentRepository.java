package com.project.ohflix.domain.content;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    // contentUpdateLinkPage
    Optional<Content> findById(@Param("id") Integer id);

    // videoManage
    List<Content> findAll();

    // 최신 컨텐츠 페이지
    @Query("""
            SELECT c
            FROM Content c
            ORDER BY c.createdAt DESC
            """)
    List<Content> findLatestContent();

    // 멤버쉽 해지 페이지에 뿌릴 컨텐츠 포스터
    @Query("""
            SELECT c
            FROM Content c
            ORDER BY c.createdAt DESC
            """)
    List<Content> findLatestContentCancelPlan();

    List<Content> findALLByOrderByIdDesc();

    // 메인 페이지 제일 인기영상을 화면에 뿌리기
    @Query("SELECT c FROM Content c ORDER BY c.viewCount DESC")
    Page<Content> findOneMostViewed(Pageable pageable);

    // 시청률 높은걸로 인기순 정렬 TOP 10
    @Query("SELECT c FROM Content c ORDER BY c.viewCount DESC")
    List<Content> findTop10(Pageable pageable);

    // 최신컨텐츠 10개 반환
    @Query("select c from Content c order by c.createdAt desc")
    List<Content> findNewVideos(Pageable pageable);

    // 메인페이지 navbar 최신 미니 아이템
    @Query("select c from Content c order by c.createdAt desc")
    List<Content> findNewFive(Pageable fiveItems);
}
