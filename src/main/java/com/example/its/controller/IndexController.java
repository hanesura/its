package com.example.its.controller;

import com.example.its.service.EventService;
import com.example.its.service.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    private final IndexService indexService;
    private final EventService eventService;

    public IndexController(IndexService indexService, EventService eventService) {
        this.indexService = indexService;
        this.eventService = eventService;
    }

    // Get /
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("activePage", "home");
        model.addAttribute("eventList", eventService.findEventList());

        indexService.InsertLog(request);
        return "index";
    }
}
