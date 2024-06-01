package com.example.its.controller;

import com.example.its.entity.ChartEntity;
import com.example.its.service.FightersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/fighter")

public class FighterController {
    private final FightersService fightersService;

    public FighterController(FightersService fightersService) {
        this.fightersService = fightersService;
    }

    // GET /fighter/{fighterId}
    @GetMapping("/{fighterId}")
    public String showDetail(@PathVariable("fighterId") int fighterId, Model model) throws JsonProcessingException {
        model.addAttribute("fighter", fightersService.findDetails(fighterId));
        model.addAttribute("fighterHistory", fightersService.findFightHistory(fighterId));
        model.addAttribute("fighterTitleHistory", fightersService.findFighterTitleHistory(fighterId));

        List<ChartEntity> chartData = fightersService.findPointChartData(fighterId);
        model.addAttribute("chartDataJson", new ObjectMapper().writeValueAsString(chartData));

        model.addAttribute("pointChartData", fightersService.findPointChartData(fighterId));
        model.addAttribute("originalCss", "/css/fighter.css");
        model.addAttribute("useChartJs", true);
        model.addAttribute("originalJs", "/js/fighter.js");
        return "myapp/fighter";

    }

}
