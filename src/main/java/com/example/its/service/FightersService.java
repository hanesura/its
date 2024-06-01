package com.example.its.service;

import com.example.its.entity.ChartEntity;
import com.example.its.entity.FighterEntity;
import com.example.its.entity.FighterHistoryEntity;
import com.example.its.entity.FighterTitleHistoryEntity;
import com.example.its.repository.FighterRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FightersService {
    private final FighterRepository fighterRepository;

    public FightersService(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;
    }

    /**
     * ファイターの詳細を取得
     * @param fighterId
     * @return
     */
    public FighterEntity findDetails(int fighterId) {
        return fighterRepository.findFighterDetails(fighterId);
    }

    /**
     * ファイターの戦績詳細を取得
     * @param fighterId
     * @return
     */
    public List<FighterHistoryEntity> findFightHistory(int fighterId) {
        return fighterRepository.findFighterHistory(fighterId);
    }

    public List<FighterTitleHistoryEntity> findFighterTitleHistory(int fighterId) {
        return fighterRepository.findFighterTitleHistory(fighterId);
    }

    public List<ChartEntity> findPointChartData(int fighterId) {
        return fighterRepository.findFighterChartEntity(fighterId);
    }

}
