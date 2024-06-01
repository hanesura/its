package com.example.its.controller;

import com.example.its.service.FightersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fighters")

public class FightersController {
    private final FightersService fightersService;

    public FightersController(FightersService fightersService) {
        this.fightersService = fightersService;
    }

    // GET /fighters
    @GetMapping
    public String showList(Model model) {
        // model.addAttribute("issueList", issueService.findAll());
        model.addAttribute("activePage", "fighters");
        return "myapp/fighters";
    }

}
