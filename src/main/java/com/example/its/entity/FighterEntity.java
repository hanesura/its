package com.example.its.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FighterEntity {
    private int fighterId;
    private String fighterName;
    private String birthday;
    private String age;
    private String height;
    private String reach;
    private String weight;
    private String totalCnt;
    private String winCnt;
    private String loseCnt;
    private String xUrl;
    private String instaUrl;
    private String youtubeUrl;
    private boolean champFlg;
    private String firstRating;
    private String point;
    private String power;
    private String speed;
    private String stand;
    private String submission;
    private String fightingSpirit;
    private String weightClassName;
    /*
    private String rizinTotalRecord;
    private String rizinWinRecord;
    private String rizinLoseRecord;
    private String[] rizinGetTitles;
    */
}
