package com.bdev.hogwarts_api.service.event_participant;

import com.bdev.hogwarts_api.data.dto.events.EventParticipant;

import java.util.List;
import java.util.Optional;

public interface EventParticipantService {
    List<EventParticipant> getAllParticipants();
    Optional<EventParticipant> getEventParticipant(long eventParticipantId);
    List<EventParticipant> getAllEventParticipants(long eventId);
    Long createParticipant(EventParticipant eventParticipant);
    void editParticipant(EventParticipant eventParticipant);
    void deleteParticipant(long eventParticipantId);
}
