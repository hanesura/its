package com.example.its.controller;

import com.example.its.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")

public class EventController {
    private final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // GET /event/{eventId}
    @GetMapping("/{eventId}")
    public String showEvent(@PathVariable("eventId") int eventId, Model model) {
        model.addAttribute("event", eventService.findEventDetails(eventId));
        model.addAttribute("vsCard", eventService.findVsCard(eventId));
        return "myapp/event";
    }

}
