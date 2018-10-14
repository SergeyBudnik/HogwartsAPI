package com.bdev.hogwarts_api.rest_service.event_participants;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.events.EventParticipant;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
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
    public List<EventParticipant> getAllParticipants(MunicipaliUserInfo userInfo) {
        return eventParticipantService.getAllParticipants();
    }

    @Override
    @Transactional(readOnly = true)
    public EventParticipant getEventParticipant(MunicipaliUserInfo userInfo, long eventParticipantId) {
        return eventParticipantService
                .getEventParticipant(eventParticipantId)
                .orElseThrow(() -> new HttpEntityNotFoundException(""));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventParticipant> getEventParticipants(
            MunicipaliUserInfo userInfo,
            long eventId
    ) {
        return eventParticipantService.getAllEventParticipants(eventId);
    }

    @Override
    @Transactional
    public long createEventParticipant(
            MunicipaliUserInfo userInfo,
            EventParticipant eventParticipant
    ) {
        return eventParticipantService.createParticipant(eventParticipant);
    }

    @Override
    @Transactional
    public void editEventParticipant(
            MunicipaliUserInfo userInfo,
            EventParticipant eventParticipant
    ) {
        eventParticipantService.editParticipant(eventParticipant);
    }

    @Override
    @Transactional
    public void deleteEventParticipant(MunicipaliUserInfo userInfo, long eventParticipantId) {
        eventParticipantService.deleteParticipant(eventParticipantId);
    }
}
