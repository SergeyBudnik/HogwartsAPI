package com.bdev.hogwarts_api.rest_service.event_participants;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.events.EventParticipant;
import com.bdev.hogwarts_api.service.event_participant.EventParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventParticipantsRestServiceImpl implements EventParticipantsRestService {
    @Autowired
    private EventParticipantService eventParticipantService;

    @Override
    @Transactional(readOnly = true)
    public List<EventParticipant> getEventParticipants(
            MunicipaliUserInfo userInfo,
            long eventId
    ) {
        return eventParticipantService.getAllEventParticipants(eventId);
    }
}
