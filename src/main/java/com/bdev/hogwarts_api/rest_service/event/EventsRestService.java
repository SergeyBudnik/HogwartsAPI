package com.bdev.hogwarts_api.rest_service.event;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.events.Event;

import java.util.List;

public interface EventsRestService {
    List<Event> getAll(MunicipaliUserInfo userInfo);
    Event getById(MunicipaliUserInfo userInfo, long id);
    long create(MunicipaliUserInfo userInfo, Event speakingClub);
    void update(MunicipaliUserInfo userInfo, Event speakingClub);
    void delete(MunicipaliUserInfo userInfo, long id);
}
