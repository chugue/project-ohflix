package com.project.ohflix.domain.content;


import com.project.ohflix._core.utils.FilenameFormatUtil;
import com.project.ohflix.domain._enums.Genre;
import com.project.ohflix.domain._enums.Rate;
import lombok.Data;

public class ContentResponse {
    // content-update-link 페이지
    @Data
    public static class ContentUpdateLinkDTO {
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

        public ContentUpdateLinkDTO(Content content) {
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
}

