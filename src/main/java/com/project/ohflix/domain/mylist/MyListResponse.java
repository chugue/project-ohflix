package com.project.ohflix.domain.mylist;


import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class MyListResponse {

    @Data
    public static class MyListDTO {
        private ProfileIcon profileIcon;
        private List<MyFavoriteList> myFavoriteList;
        private List<MyWatchList> myWatchList;


        public MyListDTO(User user, List<MyList> myFavoriteList, List<MyList> myWatcheList) {
            this.profileIcon = user.getProfileIcon();
            this.myFavoriteList = myFavoriteList.stream().map(MyFavoriteList::new).toList();
            this.myWatchList = myWatcheList.stream().map(MyWatchList::new).toList();
        }

        @Data
        public static class MyFavoriteList {
            private Integer id;             // PKMyFavoriteListDTO
            private Content content;        // 콘텐츠 테이블
            private Timestamp createdAt;
            private Double playedTime;      // 이어보기 재생시간

            public MyFavoriteList(MyList myList) {
                this.id = myList.getId();
                this.content = myList.getContent();
                this.createdAt = myList.getCreatedAt();
                this.playedTime = myList.getPlayedTime();
            }

        }
        @Data
        public static class MyWatchList {
            private Integer id;             // PKMyFavoriteListDTO
            private Content content;        // 콘텐츠 테이블
            private Timestamp createdAt;
            private Double playedTime;      // 이어보기 재생시간

            public MyWatchList(MyList myList) {
                this.id = myList.getId();
                this.content = myList.getContent();
                this.createdAt = myList.getCreatedAt();
                this.playedTime = myList.getPlayedTime() != null
                        ? myList.getPlayedTime()/myList.getContent().getRealPlayTime() *100
                        : 0.0;
            }

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
