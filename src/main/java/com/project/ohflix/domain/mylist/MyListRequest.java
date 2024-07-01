package com.project.ohflix.domain.mylist;

import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.content.ContentRequest;
import com.project.ohflix.domain.user.User;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;

import static com.project.ohflix.domain._enums.WatchOrFav.WATCHING;

public class MyListRequest {

    @Data
    public static class AddFavoriteDTO {
        private Integer userId;
        private Integer contentId;
        private WatchOrFav watchOrFav;
    }

    @Data
    public static class RemoveFavoriteDTO {
        private Integer userId;
        private Integer contentId;
        private WatchOrFav watchOrFav;
    }

}
