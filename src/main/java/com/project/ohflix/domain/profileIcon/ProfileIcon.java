package com.project.ohflix.domain.profileIcon;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "profile_icon_tb")
public class ProfileIcon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String name;

    private Boolean main;
    private Boolean mostUse;
    private Boolean funny;
    private Boolean popular;
    private Boolean joohoChildFirst;
    private Boolean joohoChildSecond;

    @Builder
    public ProfileIcon(Integer id, String path, String name, Boolean main, Boolean mostUse, Boolean funny, Boolean popular, Boolean joohoChildFirst, Boolean joohoChildSecond) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.main = main;
        this.mostUse = mostUse;
        this.funny = funny;
        this.popular = popular;
        this.joohoChildFirst = joohoChildFirst;
        this.joohoChildSecond = joohoChildSecond;
    }
}
