package com.project.ohflix.domain.content;


import com.project.ohflix.domain._enums.Genre;
import com.project.ohflix.domain._enums.Rate;
import lombok.Data;

import java.sql.Timestamp;

public class ContentResponse {

    @Data
    public static class LatestContentDTO {
        private Integer id;
        private String thumbnail;       // 썸네일
        private Timestamp createdAt;

        public LatestContentDTO(Content content) {
            this.id = content.getId();
            this.thumbnail = content.getThumbnail();
            this.createdAt = content.getCreatedAt();
        }
    }
}

