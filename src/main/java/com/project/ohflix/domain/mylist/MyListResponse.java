package com.project.ohflix.domain.mylist;


import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.user.User;
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
}
