package com.project.ohflix.domain.mylist;


import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

public class MyListResponse {

    @Data
    public static class MyFavoriteListDTO {
        Integer id;             // PK
        User user;              // 사용자 테이블
        Content content;        // 콘텐츠 테이블
        Timestamp createdAt;
        String playedTime;      // 이어보기 재생시간
        WatchOrFav watchOrFav;  // WATCHING or FAVORITE

        public MyFavoriteListDTO(MyList myList) {
            this.id = myList.getId();
            this.user = myList.getUser();
            this.content = myList.getContent();
            this.createdAt = myList.getCreatedAt();
            this.playedTime = myList.getPlayedTime();
            this.watchOrFav = myList.getWatchOrFav();
        }
    }

    // 찜 여부
    @Data
    public static class FavoriteCheck {
        private boolean isFavorite;

        public FavoriteCheck(boolean isFavorite) {
            this.isFavorite = isFavorite;
        }
    }

    // 찜 하기
    @Data
    @AllArgsConstructor
    public static class AddFavoriteDTO {
        private int userId;
        private int contentId;
        private WatchOrFav watchOrFav;

        public AddFavoriteDTO(MyListRequest.AddFavoriteDTO reqDTO) {
            this.userId = reqDTO.getUserId();
            this.contentId = reqDTO.getContentId();
            this.watchOrFav = reqDTO.getWatchOrFav();
        }
    }

    // 찜 취소
    @Data
    @AllArgsConstructor
    public static class RemoveFavoriteDTO {
        private int userId;
        private int contentId;
        private WatchOrFav watchOrFav;

        public RemoveFavoriteDTO(MyListRequest.RemoveFavoriteDTO reqDTO) {
            this.userId = reqDTO.getUserId();
            this.contentId = reqDTO.getContentId();
            this.watchOrFav = reqDTO.getWatchOrFav();
        }
    }
}
