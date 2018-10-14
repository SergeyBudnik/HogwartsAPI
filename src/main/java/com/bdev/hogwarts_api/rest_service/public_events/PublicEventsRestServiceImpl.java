package com.bdev.hogwarts_api.rest_service.public_events;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.dto.events.EventParticipant;
import com.bdev.hogwarts_api.data.dto.events.EventType;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
import com.bdev.hogwarts_api.service.event_participant.EventParticipantService;
import com.bdev.hogwarts_api.service.events.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    }
}
