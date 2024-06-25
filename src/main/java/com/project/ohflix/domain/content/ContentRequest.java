package com.project.ohflix.domain.content;


import com.project.ohflix.domain._enums.Genre;
import com.project.ohflix.domain._enums.Rate;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
            Rate entityRate = null;
            Genre entityGenre = null;
            for (Rate enumRate : Rate.values()) {
                if (enumRate.getValue().equalsIgnoreCase(rate)) {
                    entityRate=enumRate;
                }
            }
            for (Genre enumGenre : Genre.values()) {
                if (enumGenre.getValue().equalsIgnoreCase(genre)) {
                    entityGenre=enumGenre;
                }
            }
            return Content.builder()
                    .title(title)
                    .thumbnail("src/main/resources/static/images/movie/"+thumbnail.getOriginalFilename())
                    .mainPhoto("src/main/resources/static/images/movie/"+mainPhoto.getOriginalFilename())
                    .posterPhoto("src/main/resources/static/images/movie/"+posterPhoto.getOriginalFilename())
                    .textPhoto("src/main/resources/static/images/movie/"+textPhoto.getOriginalFilename())
                    .videoPath(fileName)
                    .director(director)
                    .introduction(introduction)
                    .characteristic(characteristic)
                    .playTime(String.valueOf(playTime))
                    .productYear(productYear)
                    .writers(writers)
                    .actors(actors)
                    .rate(entityRate)
                    .genre(entityGenre)
                    .build();
        }
    }

}
