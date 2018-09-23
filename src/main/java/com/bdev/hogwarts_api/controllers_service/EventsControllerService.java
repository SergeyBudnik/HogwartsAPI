package com.bdev.hogwarts_api.controllers_service;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.dto.events.EventType;

public interface EventsControllerService {
    Event getCurrentEvent(EventType eventType);
}
