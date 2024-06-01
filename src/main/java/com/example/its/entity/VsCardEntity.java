package com.example.its.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VsCardEntity {
    private Short fightOrder;
    private String fightRule;
    private String catchWeight;
    private String fighterKekka1;
    private int fighterId1;
    private String fighterNm1;
    private String fighterKekka2;
    private int fighterId2;
    private String fighterNm2;
    private String titleName;
    private String methodName;
    private String finishRound;
    private String finishTime;
    private String methodDetail;
    private String memo;
    private String lastRating1;
    private String lastRating2;
    private String winPercent1;
    private String winPercent2;
}
