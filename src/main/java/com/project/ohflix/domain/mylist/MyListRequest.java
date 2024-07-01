package com.project.ohflix.domain.mylist;

import com.project.ohflix.domain._enums.WatchOrFav;
import com.project.ohflix.domain.content.Content;
import com.project.ohflix.domain.content.ContentRequest;
import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.watchingHistory.WatchingHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

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
//    @Data
//    public static class OpenAiQuestDTO{
//        private List<WatchingHistoryDTO> watchingHistory;
//        private List<ContentDTO> contents;
//
//        public OpenAiQuestDTO(List<WatchingHistory> watchingHistory, List<Content> contents) {
//            this.watchingHistory = watchingHistory.stream().map(WatchingHistoryDTO::new).toList();
//            this.contents = contents.stream().map(ContentDTO::new).toList();
//        }
//
//        @Data
//        public static class WatchingHistoryDTO {
//
//            private Integer id;
//
//            private String contentTitle;
//
//            private Double playedTime;
//
//            private Timestamp createdAt;
//
//            public WatchingHistoryDTO(WatchingHistory watchingHistory) {
//                this.id = watchingHistory.getId();
//                this.contentTitle = watchingHistory.getContent().getTitle();
//                this.playedTime = watchingHistory.getPlayedTime();
//                this.createdAt = watchingHistory.getCreatedAt();
//            }
//        }
//
//        @Data
//        public static class ContentDTO {
//
//            private Integer id;
//
//            private String contentTitle;
//
//            public ContentDTO(Content content) {
//                this.id = content.getId();
//                this.contentTitle = content.getTitle();
//            }
//
//        }
//    }

    //openai 보낼 영화기록 데이터
    @Data
    public static class WatchingHistoryDTO {

        private Integer id;

        private String contentTitle;

        private Double playedTime;

        private Timestamp createdAt;

        public WatchingHistoryDTO(WatchingHistory watchingHistory) {
            this.id = watchingHistory.getId();
            this.contentTitle = watchingHistory.getContent().getTitle();
            this.playedTime = watchingHistory.getPlayedTime();
            this.createdAt = watchingHistory.getCreatedAt();
        }
    }

    //openai 보낼 영화 데이터
    @Data
    public static class ContentDTO {

        private Integer id;

        private String contentTitle;

        public ContentDTO(Content content) {
            this.id = content.getId();
            this.contentTitle = content.getTitle();
        }

        public ContentDTO(Integer id, String contentTitle) {
            this.id = id;
            this.contentTitle = contentTitle;
        }
    }

    //openai 응답할 restapi
    @NoArgsConstructor
    @Data
    static class OpenAIRequest {
        private String model;
        private String message;

        public OpenAIRequest(String model, String message) {
            this.model = model;
            this.message = message;
        }

    }


}
