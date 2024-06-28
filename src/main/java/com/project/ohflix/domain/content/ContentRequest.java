package com.project.ohflix.domain.content;


import com.project.ohflix.domain._enums.Genre;
import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain.mylist.MyList;
import com.project.ohflix.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.Instant;

import static com.project.ohflix.domain._enums.WatchOrFav.WATCHING;

public class ContentRequest {

    @Data
    public class AdminUploadDTO{
        private String fileName;
        private String title;
        private String productYear;
        private int playTime;
        private String rate;
        private String genre;
        private String director;
        private String actors;
        private String writers;
        private String subDirector;
        private String characteristic;
        private String introduction;
        private MultipartFile thumbnail;
        private MultipartFile mainPhoto;
        private MultipartFile posterPhoto;
        private MultipartFile textPhoto;

        public Content toEntity() {
            String pasingFileName=fileName.toString().replace(".mp4", "");
            return Content.builder()
                    .title(title)
                    .thumbnail("src/main/resources/static/images/movie/"+thumbnail.getOriginalFilename())
                    .mainPhoto("src/main/resources/static/images/movie/"+mainPhoto.getOriginalFilename())
                    .posterPhoto("src/main/resources/static/images/movie/"+posterPhoto.getOriginalFilename())
                    .textPhoto("src/main/resources/static/images/movie/"+textPhoto.getOriginalFilename())
                    .videoPath("videolocation/"+pasingFileName+"/"+pasingFileName+".mpd")
                    .director(director)
                    .introduction(introduction)
                    .characteristic(characteristic)
                    .playTime(String.valueOf(playTime))
                    .productYear(productYear)
                    .writers(writers)
                    .actors(actors)
                    .rate(Rate.valueOf(rate))
                    .genre(Genre.valueOf(genre))
                    .build();
        }
    }

    @Data
    public static class VideoProgressDTO {
        private String filename;
        private Double currentTime;

        public VideoProgressDTO() {
        }

        public VideoProgressDTO(String filename, Double currentTime) {
            this.filename = filename;
            this.currentTime = currentTime;
        }
        public MyList toEntity(User sessionUser, Content content, ContentRequest.VideoProgressDTO videoProgressDTO){
            return MyList.builder()
                    .playedTime(videoProgressDTO.getCurrentTime())
                    .watchOrFav(WATCHING)
                    .createdAt(Timestamp.from(Instant.now()))
                    .user(sessionUser)
                    .content(content)
                    .build();
        }
    }

}
