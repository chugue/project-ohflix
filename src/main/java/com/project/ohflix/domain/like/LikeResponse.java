package com.project.ohflix.domain.like;

import lombok.Data;

import java.util.Optional;

public class LikeResponse {

    @Data
    public static class LikeStatus {
        private Integer userId;
        private Integer contentId;
        private Boolean isLiked;

        public LikeStatus(Integer userId, Integer contentId, Optional<Like> like){
            this.userId = userId;
            this.contentId = contentId;
            if (like.isPresent()){
                this.isLiked = true;
            } else {
                this.isLiked = false;
            }
        }
    }

    // 좋아요 하기
    @Data
    public static class AddLikeDTO {
        private Integer userId;
        private Integer contentId;
        private Boolean isLiked = true;


        public AddLikeDTO(Like like) {
            this.userId = like.getUser().getId();
            this.contentId = like.getContent().getId();
        }
    }

    // 좋아요 취소
    @Data
    public static class RemoveLikeDTO {
        private Integer userId;
        private Integer contentId;
        private Boolean isLiked = false;


        public RemoveLikeDTO(Like like) {
            this.userId = like.getUser().getId();
            this.contentId = like.getContent().getId();

        }
    }
}
