package com.bdev.hogwarts_api.data.converter.event_participant;

import com.bdev.hogwarts_api.data.dto.events.EventParticipant;
import com.bdev.hogwarts_api.data.model.event_participant.EventParticipantModel;

public class EventParticipantDtoConverter {
    public static EventParticipantModel convert(EventParticipant eventParticipant) {
        EventParticipantModel eventParticipantModel = new EventParticipantModel();

        eventParticipantModel.setId(eventParticipant.getId());
        eventParticipantModel.setEventId(eventParticipant.getEventId());
        eventParticipantModel.setName(eventParticipant.getName());
        eventParticipantModel.setStatus(eventParticipant.getStatus());
        eventParticipantModel.setPhone(eventParticipant.getPhone());
        eventParticipantModel.setReferralSource(eventParticipant.getReferralSource());

        return eventParticipantModel;
    }
}
