package com.project.ohflix.domain.profileIcon;


import lombok.Data;

public class ProfileIconResponse {
    @Data
    public static class ProfileIconListDTO {
        Integer id;
        String path;
        String name;
        Boolean main;               // 대표
        Boolean mostUse;            // 자주 사용하는
        Boolean funny;              // 재밌는
        Boolean popular;            // 인기있는
        Boolean joohoChildFirst;    // 주호아이들1
        Boolean joohoChildSecond;   // 주호아이들2

        public ProfileIconListDTO(ProfileIcon profileIcon) {
            this.id = profileIcon.getId();
            this.path = profileIcon.getPath();
            this.name = profileIcon.getName();
            this.main = profileIcon.getMain();
            this.mostUse = profileIcon.getMostUse();
            this.funny = profileIcon.getFunny();
            this.popular = profileIcon.getPopular();
            this.joohoChildFirst = profileIcon.getJoohoChildFirst();
            this.joohoChildSecond = profileIcon.getJoohoChildSecond();
        }
    }
}

