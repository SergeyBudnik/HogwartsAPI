package com.bdev.hogwarts_api.service.events;

import com.bdev.hogwarts_api.data.dto.events.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> getAll();
    Optional<Event> getById(long id);
    boolean exists(long id);
    long create(Event speakingClub);
    void update(Event speakingClub);
    void delete(long id);
}
