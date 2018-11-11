package com.bdev.hogwarts_api.data.converter.event_participant;

import com.bdev.hogwarts_api.data.dto.events.EventParticipant;
import com.bdev.hogwarts_api.data.model.event_participant.EventParticipantModel;
import com.bdev.hogwarts_api.utils.EncodingUtils;

public class EventParticipantModelConverter {
    public static EventParticipant convert(EventParticipantModel eventParticipantModel) {
        return EventParticipant
                .builder()
                .id(eventParticipantModel.getId())
                .eventId(eventParticipantModel.getEventId())
                .name(EncodingUtils.fromBase64(eventParticipantModel.getName()))
                .status(eventParticipantModel.getStatus())
                .phone(eventParticipantModel.getPhone())
                .referralSource(eventParticipantModel.getReferralSource())
                .enlisted(eventParticipantModel.getEnlisted())
                .build();
    }
}
