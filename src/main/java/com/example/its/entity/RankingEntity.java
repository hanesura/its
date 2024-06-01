package com.example.its.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RankingEntity {
    private long no;
    private int fighterId;
    private String fighterName;
    private boolean champFlg;
    private String point;
}
