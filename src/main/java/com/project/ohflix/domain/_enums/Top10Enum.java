package com.project.ohflix.domain._enums;

import lombok.Getter;

@Getter
public enum Top10Enum {

    RANK1("/static/images/rank_svg/rank01.svg"),
    RANK2("/static/images/rank_svg/rank02.svg"),
    RANK3("/static/images/rank_svg/rank03.svg"),
    RANK4("/static/images/rank_svg/rank04.svg"),
    RANK5("/static/images/rank_svg/rank05.svg"),
    RANK6("/static/images/rank_svg/rank06.svg"),
    RANK7("/static/images/rank_svg/rank07.svg"),
    RANK8("/static/images/rank_svg/rank08.svg"),
    RANK9("/static/images/rank_svg/rank09.svg"),
    RANK10("/static/images/rank_svg/rank10.svg");

    private final String value;

    Top10Enum(String value) {
        this.value = value;
    }

}

