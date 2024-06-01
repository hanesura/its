package com.example.its.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WeightEntity {
    private short weightId;
    private String weightName;
    private String weightLimit;
    private Integer fighterId;
    private String fighterName;
}
