package com.bdev.hogwarts_api.rest_service.public_events;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.dto.events.EventParticipant;
import com.bdev.hogwarts_api.data.dto.events.EventType;

public interface PublicEventsRestService {
    Event getCurrentEvent(EventType eventType);
    void addEventParticipant(EventParticipant eventParticipant);
}
