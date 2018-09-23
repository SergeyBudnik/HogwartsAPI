package com.bdev.hogwarts_api.service.event_participant;

import com.bdev.hogwarts_api.data.dto.events.EventParticipant;

import java.util.List;

public interface EventParticipantService {
    List<EventParticipant> getAllEventParticipants(long eventId);
    Long createParticipant(EventParticipant eventParticipant);
}
