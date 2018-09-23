package com.bdev.hogwarts_api.rest_service.event;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
import com.bdev.hogwarts_api.service.events.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventsRestServiceImpl implements EventsRestService {
    @Autowired
    private EventService speakingClubService;

    @Override
    @Transactional(readOnly = true)
    public List<Event> getAll(MunicipaliUserInfo userInfo) {
        return speakingClubService.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Event getById(MunicipaliUserInfo userInfo, long id) {
        return speakingClubService
                .getById(id)
                .orElseThrow(() -> new HttpEntityNotFoundException("Event with id '%d' does not exist", id));
    }

    @Override
    @Transactional
    public long create(MunicipaliUserInfo userInfo, Event speakingClub) {
        if (speakingClub.getId() != null) {
            throw new HttpEntityIllegalStateException("Event id should be null during creation");
        }

        return speakingClubService.create(speakingClub);
    }

    @Override
    @Transactional
    public void update(MunicipaliUserInfo userInfo, Event speakingClub) {
        if (speakingClub.getId() == null) {
            throw new HttpEntityIllegalStateException("Event id should be non-null during edit");
        }

        if (!speakingClubService.exists(speakingClub.getId())) {
            throw new HttpEntityNotFoundException("Event with id '%d' does not exist", speakingClub.getId());
        }

        speakingClubService.update(speakingClub);
    }

    @Override
    @Transactional
    public void delete(MunicipaliUserInfo userInfo, long id) {
        if (!speakingClubService.exists(id)) {
            throw new HttpEntityNotFoundException("Event with id '%d' does not exist", id);
        }

        speakingClubService.delete(id);
    }
}
