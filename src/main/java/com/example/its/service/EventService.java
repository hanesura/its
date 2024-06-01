package com.example.its.service;

import com.example.its.entity.*;
import com.example.its.repository.EventRepository;
import com.example.its.repository.FighterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * イベントリストを取得
     * @return
     */
    public List<EventEntity> findEventList() {
        return eventRepository.findEventList();
    }
    /**
     * イベントの詳細を取得
     * @param eventId
     * @return
     */
    public EventEntity findEventDetails(int eventId) {
        return eventRepository.findEventDetails(eventId);
    }
    /**
     * 対戦カードを取得
     * @param eventId
     * @return
     */
    public List<VsCardEntity> findVsCard(int eventId) {
        return eventRepository.findVsCard(eventId);
    }
}
