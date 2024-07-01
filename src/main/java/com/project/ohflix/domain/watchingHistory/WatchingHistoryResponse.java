package com.project.ohflix.domain.watchingHistory;


import com.project.ohflix.domain.user.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WatchingHistoryResponse {

    @Data
    public static class WatchingHistoryDTO {

        private String nickname;
        private String profileIcon;
        private List<WatchingHistoriesDTO> WatchingHistories=new ArrayList<>();

        @Data
        public static class WatchingHistoriesDTO {
            private String watchingTime;
            private String contentTitle;

            public WatchingHistoriesDTO(WatchingHistory watchingHistory) {
                LocalDateTime dateTime = watchingHistory.getCreatedAt().toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                this.watchingTime = dateTime.format(formatter);
                this.contentTitle = watchingHistory.getContent().getTitle();
            }
        }

        public WatchingHistoryDTO(User user, List<WatchingHistory> watchingHistories) {
            this.nickname = user.getNickname();
            this.profileIcon = user.getProfileIcon().getPath();
            WatchingHistories = watchingHistories.stream().map(WatchingHistoriesDTO::new).toList();
        }
    }
}
