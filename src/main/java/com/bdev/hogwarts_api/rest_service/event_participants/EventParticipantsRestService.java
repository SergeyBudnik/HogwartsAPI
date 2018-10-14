package com.bdev.hogwarts_api.rest_service.event_participants;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.events.EventParticipant;

import java.util.List;

public interface EventParticipantsRestService {
    List<EventParticipant> getAllParticipants(MunicipaliUserInfo userInfo);
    EventParticipant getEventParticipant(MunicipaliUserInfo userInfo, long eventParticipantId);
    List<EventParticipant> getEventParticipants(MunicipaliUserInfo userInfo, long eventId);
    long createEventParticipant(MunicipaliUserInfo userInfo, EventParticipant eventParticipant);
    void editEventParticipant(MunicipaliUserInfo userInfo, EventParticipant eventParticipant);
    void deleteEventParticipant(MunicipaliUserInfo userInfo, long eventParticipantId);
}
