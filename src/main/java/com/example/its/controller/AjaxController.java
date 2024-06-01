package com.example.its.controller;

import com.example.its.entity.FighterEntity;
import com.example.its.entity.RankingEntity;
import com.example.its.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AjaxController {
    private final RankingService rankingService;

    @GetMapping("/getTest")
    public String getTest() {
        return "test_Test";
    }
    @PostMapping("/getRanking")
    // ランキング取得
    public List<RankingEntity> getRanking(@RequestParam int inputText) {
        List<RankingEntity> resList = new ArrayList<>();

        resList = rankingService.findRankingList(inputText);
        return resList;
    }
    @GetMapping("/search")
    public List<RankingEntity> searchPlayers(@RequestParam String query) {
        List<RankingEntity> allPlayers = rankingService.findRankingListByFighterName(query);
        return allPlayers;
/*
        return allPlayers.stream()
                .filter(player -> player.getFighterName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
*/
    }
}
