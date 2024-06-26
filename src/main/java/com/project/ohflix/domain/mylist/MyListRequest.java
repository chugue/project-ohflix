package com.project.ohflix.domain.mylist;

import com.project.ohflix.domain._enums.WatchOrFav;
import lombok.Data;

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
