package com.project.ohflix.domain._enums;

import lombok.Getter;

@Getter
public enum Genre {

    ANIME("애니메이션"),
    ACTION("액션"),
    COMEDY("코미디"),
    ROMANCE("로맨스"),
    THRILLER("스릴러"),
    HORROR("호러"),
    SF("SF"),
    FANTASY("판타지"),
    DOCUMENTARY("다큐멘터리");

    private final String value;

    Genre(String value) {
        this.value = value;
    }
}
