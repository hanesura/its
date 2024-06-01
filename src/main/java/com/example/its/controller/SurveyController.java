package com.example.its.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/survey")
@RequiredArgsConstructor

public class SurveyController {

    // GET /survey
    @GetMapping
    public String showList(Model model) {
        // model.addAttribute("issueList", issueService.findAll());
        model.addAttribute("activePage", "survey");
        return "myapp/survey";
    }
}
