package com.example.its.controller;

import com.example.its.service.IndexService;
import com.example.its.service.RankingService;
import com.example.its.util.ConstUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {
    private final IndexService indexService;
    private final RankingService rankingService;
    private final ConstUtil cUtil = new ConstUtil();

    // GET /ranking
    @GetMapping
    public String showList(Model model, HttpServletRequest request) {
        // 初期値はフェザー級を取得
        int activeWeightClass = 2;
        model.addAttribute("rankingType", "point");
        model.addAttribute("activeWeightClassName", cUtil.getWeightClassName(activeWeightClass)+"ポイント");
        model.addAttribute("activeWeightClass", activeWeightClass);
        model.addAttribute("weightList", rankingService.findWeightList());
        model.addAttribute("rankingList", rankingService.findRankingList(activeWeightClass));
        model.addAttribute("activePage", "ranking");
        model.addAttribute("originalCss", "/css/ranking.css");
        model.addAttribute("originalJs", "/js/ranking.js");

        indexService.InsertLog(request);

        return "myapp/ranking";
    }

    // GET /ranking/p4p
    @GetMapping("/p4p")
    public String showP4PList(Model model) {
        model.addAttribute("rankingType", "p4p");
        model.addAttribute("activeWeightClassName", "P4Pポイント");

        model.addAttribute("rankingList", rankingService.findP4PRankingList());
        model.addAttribute("activePage", "ranking");
        model.addAttribute("originalCss", "/css/ranking.css");
        model.addAttribute("originalJs", "/js/ranking.js");
        return "myapp/ranking";
    }

    // GET /ranking/fightcnt
    @GetMapping("/fightcnt")
    public String showFightCntList(Model model) {
        model.addAttribute("rankingType", "fightcnt");
        model.addAttribute("activeWeightClassName", "出場回数");

        model.addAttribute("rankingList", rankingService.findRankingListByFightCnt());
        model.addAttribute("activePage", "ranking");
        model.addAttribute("originalCss", "/css/ranking.css");
        model.addAttribute("originalJs", "/js/ranking.js");
        return "myapp/ranking";
    }
    // GET /ranking/fightcnt
    @GetMapping("/maincnt")
    public String showMainCntList(Model model) {
        model.addAttribute("rankingType", "maincnt");
        model.addAttribute("activeWeightClassName", "メインイベント出場回数");

        model.addAttribute("rankingList", rankingService.findRankingListByMainCnt());
        model.addAttribute("activePage", "ranking");
        model.addAttribute("originalCss", "/css/ranking.css");
        model.addAttribute("originalJs", "/js/ranking.js");
        return "myapp/ranking";
    }
}
