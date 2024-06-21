package com.project.ohflix.domain.content;


import com.project.ohflix.domain._enums.Genre;
import com.project.ohflix.domain._enums.Rate;
import lombok.Data;

import java.sql.Timestamp;

public class ContentResponse {

    @Data
    public static class LatestContentDTO {
        Integer id;
        String title;           // 제목
        String thumbnail;       // 썸네일
        String videoPath;       //
        String mainPhoto;       // 대표 사진 (가로사이즈)
        String posterPhoto;     // 포스터 사진 (세로사이즈 포스터 형식 작은거, top10메뉴에 쓰임)
        String textPhoto;       // 대표 텍스트 사진
        String director;        // 감독 이름
        String introduction;    // 영화 소개
        String characteristic;  // 영화 특징
        String playTime;        // 재생 시간
        String productYear;     // 제작 연도
        String writers;         // 각본
        String actors;          // 배우
        Integer viewCount;      // 조회수 인기 컨텐츠 정보로 사용
        Rate rate;              // 관람 등급 [ PG(청소년 등급), R(성인 등급), ALL(모든 시청자 등급) ]
        Genre genre;            // 장르 [ANIME, ACTION, COMEDY, ROMANCE, THRILLER, HORROR, SF, FANTASY, DOCUMENTARY]
        Timestamp createdAt;

        public LatestContentDTO(Content content) {
            this.id = content.getId();
            this.title = content.getTitle();
            this.thumbnail = content.getThumbnail();
            this.videoPath = content.getVideoPath();
            this.mainPhoto = content.getMainPhoto();
            this.posterPhoto = content.getMainPhoto();
            this.textPhoto = content.getTextPhoto();
            this.director = content.getDirector();
            this.introduction = content.getIntroduction();
            this.characteristic = content.getCharacteristic();
            this.playTime = content.getPlayTime();
            this.productYear = content.getProductYear();
            this.writers = content.getWriters();
            this.actors = content.getActors();
            this.viewCount = content.getViewCount();
            this.rate = content.getRate();
            this.genre = content.getGenre();
            this.createdAt = content.getCreatedAt();
        }
    }
}

