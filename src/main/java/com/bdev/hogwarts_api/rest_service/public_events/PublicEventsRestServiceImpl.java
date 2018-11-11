package com.bdev.hogwarts_api.rest_service.public_events;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.dto.events.EventParticipant;
import com.bdev.hogwarts_api.data.dto.events.EventType;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
import com.bdev.hogwarts_api.service.event_participant.EventParticipantService;
import com.bdev.hogwarts_api.service.events.EventService;
import com.bdev.hogwarts_api.vk.VkMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class PublicEventsRestServiceImpl implements PublicEventsRestService {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventParticipantService eventParticipantService;

    @Override
    @Transactional(readOnly = true)
    public Event getCurrentEvent(EventType eventType) {
        return eventService
                .getLatestEvent(eventType)
                .orElseThrow(() -> new HttpEntityNotFoundException(""));
    }

    @Override
    @Transactional
    public void addEventParticipant(EventParticipant eventParticipant) {
        if (!eventService.exists(eventParticipant.getEventId())) {
            throw new HttpEntityNotFoundException("");
        }

        eventParticipantService.createParticipant(eventParticipant);

        Event event = eventService
                .getById(eventParticipant.getEventId())
                .orElseThrow(() -> new HttpEntityNotFoundException(""));

        String message =
                "Новая заявка на " + event.getName() + ".\n" +
                "Студент: " + eventParticipant.getName() + "\n" +
                "Телефон: " + eventParticipant.getPhone() + "\n" +
                "VK: ?\n" +
                "Подробнее: http://hogwarts-engschool.ru/admin/#/events/" + event.getId() + "/participants";

        List<Integer> adminIds = Arrays.asList(23236615, 30026296);

        adminIds.forEach(adminId -> new VkMessageSender().sendMessage(adminId, message));
    }
}
