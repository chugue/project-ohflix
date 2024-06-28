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
    public static class MyFavoriteListDTO {
        private ProfileIcon profileIcon;
        private List<MyFavoriteList> myFavoriteList;

        public MyFavoriteListDTO(User user, List<MyList> myFavoriteList, List<MyList> myWatchList) {
            this.profileIcon = user.getProfileIcon();
            this.myFavoriteList = myFavoriteList.stream()
                    .map(myList -> new MyFavoriteList(myList, findMyWatchList(myWatchList, myList)))
                    .toList();
        }

        private MyList findMyWatchList(List<MyList> myWatchList, MyList myList) {
            return myWatchList.stream()
                    .filter(watchList -> watchList.getContent().equals(myList.getContent()))
                    .findFirst()
                    .orElse(null);
        }

        @Data
        public static class MyFavoriteList {
            private Integer id;             // PKMyFavoriteListDTO
            private Content content;        // 콘텐츠 테이블
            private Timestamp createdAt;
            private double playedTime;      // 이어보기 재생시간

            public MyFavoriteList(MyList myList, MyList myWatchList) {
                this.id = myList.getId();
                this.content = myList.getContent();
                this.createdAt = myList.getCreatedAt();
                this.playedTime = myWatchList != null && myWatchList.getPlayedTime() != null
                        ? myList.getContent().getRealPlayTime() / myWatchList.getPlayedTime()
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
