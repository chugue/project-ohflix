package com.project.ohflix.domain.content;


import com.project.ohflix._core.utils.FilenameFormatUtil;
import com.project.ohflix.domain._enums.Genre;
import com.project.ohflix.domain._enums.Rate;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class ContentResponse {

    // 메인 페이지 모달 - 컨텐츠 상세정보 데이터
    @Data
    public static class InfoDTO {
        private Integer id;
        private String title;
        private String mainPhoto;
        private String productYear;
        private String playTime;
        private String textPhoto;
        private String actors;
        private String writers;
        private String genre;
        private String characteristics;
        private String introduction;
        private String rateImg;
        private String rate;
        private String director;

        public InfoDTO(Content content) {
            this.id = content.getId();
            this.mainPhoto = content.getMainPhoto();
            this.title = content.getTitle();
            this.productYear = content.getProductYear();
            this.playTime = content.getPlayTime();
            this.actors = content.getActors();
            this.textPhoto = content.getTextPhoto();
            this.director = content.getDirector();
            this.genre = content.getGenre().getValue();
            this.rateImg = content.getRate().getImgPath();
            this.rate = content.getRate().getValue();
            this.writers = content.getWriters();
            this.characteristics = content.getCharacteristic();
            this.introduction = content.getIntroduction();
        }
    }

    // 상세정보 페이지 데이터
    @Data
    public static class DetailsDTO {
        private Integer id;
        private String title;
        private String mainPhoto;
        private String productYear;
        private String playTime;
        private String textPhoto;
        private String actors;
        private String writers;
        private String genre;
        private String characteristics;
        private String introduction;
        private String rateImg;
        private String rate;
        private String director;

        public DetailsDTO(Content content) {
            this.id = content.getId();
            this.mainPhoto = content.getMainPhoto();
            this.title = content.getTitle();
            this.productYear = content.getProductYear();
            this.playTime = content.getPlayTime();
            this.actors = content.getActors();
            this.textPhoto = content.getTextPhoto();
            this.director = content.getDirector();
            this.genre = content.getGenre().getValue();
            this.rateImg = content.getRate().getImgPath();
            this.rate = content.getRate().getValue();
            this.writers = content.getWriters();
            this.characteristics = content.getCharacteristic();
            this.introduction = content.getIntroduction();
        }
    }

    // content-update-link 페이지
    @Data
    public static class ContentUpdateLinkPageDTO {
        private int id;
        private String title; // 영화 제목
        private String thumbnail; // 썸네일
        private String videoPath;
        private String mainPhoto; // 대표 사진 (가로 사이즈)
        private String posterPhoto; // 포스터 사진 (세로 사이즈)
        private String textPhoto; // 대표 텍스트 사진
        private String director; // 감독 이름
        private String introduction; // 영화 소개
        private String characteristic; // 영화 특징
        private String playTime; // 재생 시간
        private String productYear; // 제작 연도
        private String writers; // 각본
        private String actors; // 배우
        private Rate rate; // 관람등급
        private Genre genre; // 장르

        public ContentUpdateLinkPageDTO(Content content) {
            this.id = content.getId();
            this.title = content.getTitle();
            this.thumbnail = FilenameFormatUtil.parseThumbnailFileName(content.getThumbnail());
            this.videoPath = FilenameFormatUtil.parseThumbnailFileName(content.getVideoPath());
            this.mainPhoto = FilenameFormatUtil.parseThumbnailFileName(content.getMainPhoto());
            this.posterPhoto = FilenameFormatUtil.parseThumbnailFileName(content.getPosterPhoto());
            this.textPhoto = FilenameFormatUtil.parseThumbnailFileName(content.getTextPhoto());
            this.director = content.getDirector();
            this.introduction = content.getIntroduction();
            this.characteristic = content.getCharacteristic();
            this.playTime = content.getPlayTime();
            this.productYear = content.getProductYear();
            this.writers = content.getWriters();
            this.actors = content.getActors();
            this.rate = content.getRate();
            this.genre = content.getGenre();
        }
    }

    // video-manage 페이지
    @Data
    public static class VideoManagePageDTO {
        private List<Video> videoDTO;

        public VideoManagePageDTO(List<Content> contents) {
            this.videoDTO = contents.stream().map(content -> new Video(content)).toList();
        }

        @Data
        public static class Video {
            private int id;
            private String title; // 영화 제목
            private String thumbnail; // 썸네일

            public Video(Content content) {
                this.id = content.getId();
                this.title = content.getTitle();
                this.thumbnail = content.getThumbnail();
            }
        }
    }

    @Data
    public static class LatestContentDTO {
        private Integer id;
        private String thumbnail;       // 썸네일
        private String posterPhoto;       // 썸네일
        private Timestamp createdAt;

        public LatestContentDTO(Content content) {
            this.id = content.getId();
            this.thumbnail = content.getThumbnail();
            this.posterPhoto = content.getPosterPhoto();
            this.createdAt = content.getCreatedAt();
        }
    }

    @Data
    public static class CanclePlanPageContentDTO {
        private Integer id;
        private String posterPhoto;       // 썸네일
        private Timestamp createdAt;

        public CanclePlanPageContentDTO(Content content) {
            this.id = content.getId();
            this.posterPhoto = content.getPosterPhoto();
            this.createdAt = content.getCreatedAt();
        }
    }
}

