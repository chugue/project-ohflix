package com.project.ohflix.domain.content;

import com.project.ohflix.domain._enums.Genre;
import com.project.ohflix.domain._enums.Rate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "content_tb")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String title; // 제목

    @Column(nullable = false, length = 255)
    private String thumbnail; // 썸네일

    @Column(nullable = false, length = 255)
    private String videoPath;

    @Column(nullable = false, length = 255)
    private String mainPhoto; // 대표 사진 (가로사이즈)

    @Column(nullable = false, length = 255)
    private String posterPhoto; // 포스터 사진 (세로사이즈 포스터 형식 작은거, top10메뉴에 쓰임)

    @Column(nullable = false, length = 255)
    private String textPhoto; // 대표 텍스트 사진

    @Column(nullable = false, length = 255)
    private String director; // 감독 이름

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction; // 영화 소개

    @Column(columnDefinition = "TEXT")
    private String characteristic; // 영화 특징

    @Column(nullable = false, length = 50)
    private String playTime; // 재생 시간

    @Column(nullable = false, length = 4)
    private String productYear; // 제작 연도

    @Column(nullable = false, columnDefinition = "TEXT")
    private String writers; // 각본

    @Column(nullable = false, columnDefinition = "TEXT")
    private String actors; // 배우

    private Integer viewCount; // 조회수 인기 컨텐츠 정보로 사용

    @Enumerated(EnumType.STRING)
    private Rate rate; // 관람 등급 [ PG(청소년 등급), R(성인 등급), ALL(모든 시청자 등급) ]

    @Enumerated(EnumType.STRING)
    private Genre genre; // 장르 [ANIME, ACTION, COMEDY, ROMANCE, THRILLER, HORROR, SF, FANTASY, DOCUMENTARY]

    @Builder
    public Content(Integer id, String title, String thumbnail, String videoPath, String mainPhoto, String posterPhoto, String textPhoto, String director, String introduction, String characteristic, String playTime, String productYear, String writers, String actors, Integer viewCount, Rate rate, Genre genre) {
        this.id = id;
        this.title = title;

        this.thumbnail = thumbnail;
        this.videoPath = videoPath;
        this.mainPhoto = mainPhoto;
        this.posterPhoto = posterPhoto;
        this.textPhoto = textPhoto;
        this.director = director;
        this.introduction = introduction;
        this.characteristic = characteristic;
        this.playTime = playTime;
        this.productYear = productYear;
        this.writers = writers;
        this.actors = actors;
        this.viewCount = viewCount;
        this.rate = rate;
        this.genre = genre;
    }
}