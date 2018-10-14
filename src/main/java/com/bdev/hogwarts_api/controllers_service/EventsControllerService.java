package com.bdev.hogwarts_api.controllers_service;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.dto.events.EventType;

import java.util.Optional;

public interface EventsControllerService {
    Optional<Event> getCurrentEvent(EventType eventType);
}
