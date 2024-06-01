package com.example.its.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FighterHistoryEntity {
    private String fightDay;
    private int eventId;
    private String eventName;
    private String mainOrSemi;
    private int vsFighterId;
    private String vsFighterName;
    private String fightKekka;
    private String methodName;
    private String methodDetail;
}
