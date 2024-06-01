package com.example.its.util;

import org.springframework.stereotype.Service;

@Service
public class ConstUtil {
    public static String getWeightClassName(int weightClass) {
        switch (weightClass) {
            case 4:
                return "フライ級";
            case 2:
                return "フェザー級";
            case 3:
                return "バンタム級";
            case 1:
                return "ライト級";
            case 6:
                return "女子アトム級";
            default:
                return "未知の階級";
        }
    }
}
