package com.example.its.service;

import com.example.its.entity.RankingEntity;
import com.example.its.entity.WeightEntity;
import com.example.its.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankingService {
    private final RankingRepository rankingRepository;

    public RankingService(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    public List<WeightEntity> findWeightList() {
        return rankingRepository.findWeigntList();
    }

    public List<RankingEntity> findRankingList(int weightClass) {
        return rankingRepository.findRankingList(weightClass);
    }
    public List<RankingEntity> findP4PRankingList() {
        return rankingRepository.findP4PRankingList();
    }
    public List<RankingEntity> findRankingListByFighterName(String fighterName) {
        return rankingRepository.findRankingListByFighterName(fighterName);
    }
    public List<RankingEntity> findRankingListByFightCnt() {
        return rankingRepository.findRankingListByFightCnt();
    }
    public List<RankingEntity> findRankingListByMainCnt() {
        return rankingRepository.findRankingListByMainCnt();
    }
}
