package com.example.its.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventEntity {
    private Integer eventId;
    private String eventName;
    private String eventYmd;
    private String venueName;
    private boolean kekkaSumi;
    private Short ratingSumi;
    private String eventImgUrl;
}
