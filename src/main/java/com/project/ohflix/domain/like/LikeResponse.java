package com.project.ohflix.domain.like;

import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.mylist.MyListRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LikeResponse {

    // 좋아요 하기
    @Data
    @AllArgsConstructor
    public static class AddLikeDTO {
        private int userId;
        private int contentId;
        private Timestamp createdAt;

        public AddLikeDTO(LikeRequest.AddLikeDTO reqDTO) {
            this.userId = reqDTO.getUserId();
            this.contentId = reqDTO.getContentId();
            this.createdAt = reqDTO.getCreatedAt();
        }
    }

    // 좋아요 취소
    @Data
    @AllArgsConstructor
    public static class RemoveLikeDTO {
        private int userId;
        private int contentId;
        private Timestamp createdAt;

        public RemoveLikeDTO(LikeRequest.RemoveLikeDTO reqDTO) {
            this.userId = reqDTO.getUserId();
            this.contentId = reqDTO.getContentId();
            this.createdAt = reqDTO.getCreatedAt();
        }
    }
}
