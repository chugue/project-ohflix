package com.project.ohflix.domain.content;

import com.project.ohflix.domain._enums.Genre;
import com.project.ohflix.domain._enums.Paymethod;
import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain._enums.Status;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
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

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String thumbnail; // 썸네일

    @Column(nullable = false)
    private String miniThumbnail; // 작은 썸네일

    @Column(nullable = false)
    private String mainPhoto; // 대표 사진 (가로사이즈)

    @Column(nullable = false)
    private String subPhoto; // 대표 사진 (세로사이즈 작은거)

    @Column(nullable = false)
    private String director; // 감독 이름

    @Column(nullable = false)
    private String introduction; // 영화 소개

    private String characteristic; // 영화 특징

    @Column(nullable = false)
    private String playTime; // 재생 시간

    @Column(nullable = false)
    private String year; // 제작 연도

    @Column(nullable = false)
    private String writers; // 각본

    @Column(nullable = false)
    private String actors; // 배우

    private String subDirector; // 연출

    private Integer viewCount; // 조회수 인기 컨텐츠 정보로 사용

    @Enumerated(EnumType.STRING)
    private Rate rate; // 관람 등급 [ PG(청소년 등급), R(성인 등급), ALL(모든 시청자 등급) ]

    @Enumerated(EnumType.STRING)
    private Genre genre; // 장르 [ANIME, ACTION, COMEDY, ROMANCE, THRILLER, HORROR, SF, FANTASY, DOCUMENTARY]

    @Builder
    public Content(Integer id, String title, String thumbnail, String miniThumbnail, String mainPhoto, String subPhoto, String director, String introduction, String characteristic, String playTime, String year, String writers, String actors, String subDirector, Integer viewCount, Rate rate, Genre genre) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.miniThumbnail = miniThumbnail;
        this.mainPhoto = mainPhoto;
        this.subPhoto = subPhoto;
        this.director = director;
        this.introduction = introduction;
        this.characteristic = characteristic;
        this.playTime = playTime;
        this.year = year;
        this.writers = writers;
        this.actors = actors;
        this.subDirector = subDirector;
        this.viewCount = viewCount;
        this.rate = rate;
        this.genre = genre;
    }
}
