package com.bdev.hogwarts_api.controllers_service;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.dto.events.EventType;
import com.bdev.hogwarts_api.service.events.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EventsControllerServiceImpl implements EventsControllerService {
    @Autowired
    private EventService eventService;

    @Override
    @Transactional(readOnly = true)
    public Event getCurrentEvent(EventType eventType) {
        return eventService
                .getAll()
                .stream()
                .filter(it -> it.getEventType() == eventType)
                .findAny()
                .orElseThrow(EntityNotFoundException::new);
    }
}
