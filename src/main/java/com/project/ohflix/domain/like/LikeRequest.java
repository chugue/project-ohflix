package com.project.ohflix.domain.like;

import lombok.Data;

import java.sql.Timestamp;

public class LikeRequest {

    // 좋아요 하기
    @Data
    public static class AddLikeDTO {
        private Integer userId;
        private Integer contentId;

    }

    // 좋아요 취소
    @Data
    public static class RemoveLikeDTO {
        private Integer userId;
        private Integer contentId;

    }
}
