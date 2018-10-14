package com.bdev.hogwarts_api.service.events;

import com.bdev.hogwarts_api.dao.EventDao;
import com.bdev.hogwarts_api.data.converter.events.EventDtoConverter;
import com.bdev.hogwarts_api.data.converter.events.EventModelConverter;
import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.dto.events.EventType;
import com.bdev.hogwarts_api.data.model.events.EventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toList;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventDao eventDao;

    @Override
    public List<Event> getAll() {
        return eventDao
                .findAll()
                .stream()
                .map(EventModelConverter::convert)
                .collect(toList());
    }

    @Override
    public Optional<Event> getLatestEvent(EventType eventType) {
        return eventDao
                .getAllByEventTypeAndDateGreaterThan(eventType, new Date().getTime())
                .stream()
                .min(comparingLong(EventModel::getDate))
                .map(EventModelConverter::convert);
    }

    @Override
    public Optional<Event> getById(long id) {
        return Optional
                .ofNullable(eventDao.getOne(id))
                .map(EventModelConverter::convert);
    }

    @Override
    public boolean exists(long id) {
        return eventDao.exists(id);
    }

    @Override
    public long create(Event speakingClub) {
        if (speakingClub.getId() != null) {
            throw new RuntimeException("Event should be null during creation");
        }

        return eventDao.save(EventDtoConverter.convert(speakingClub)).getId();
    }

    @Override
    public void update(Event speakingClub) {
        if (!eventDao.exists(speakingClub.getId())) {
            throw new RuntimeException(format("Event with id '%d' does not exist", speakingClub.getId()));
        }

        eventDao.save(EventDtoConverter.convert(speakingClub));
    }

    @Override
    public void delete(long id) {
        if (!eventDao.exists(id)) {
            throw new RuntimeException(format("Event with id '%d' does not exist", id));
        }

        eventDao.delete(id);
    }
}
