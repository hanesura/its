package com.example.its.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FighterTitleHistoryEntity {
    String startYmd;
    String endYmd;
    String champName;
}
