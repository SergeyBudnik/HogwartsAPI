package com.bdev.hogwarts_api.rest_service.public_events;

import com.bdev.hogwarts_api.data.dto.events.EventParticipant;

public interface PublicEventsRestService {
    void addEventParticipant(EventParticipant eventParticipant);
}
