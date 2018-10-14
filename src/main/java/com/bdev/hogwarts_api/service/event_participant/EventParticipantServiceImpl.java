package com.bdev.hogwarts_api.service.event_participant;

import com.bdev.hogwarts_api.dao.EventParticipantDao;
import com.bdev.hogwarts_api.data.converter.event_participant.EventParticipantDtoConverter;
import com.bdev.hogwarts_api.data.converter.event_participant.EventParticipantModelConverter;
import com.bdev.hogwarts_api.data.dto.events.EventParticipant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class EventParticipantServiceImpl implements EventParticipantService {
    @Autowired
    private EventParticipantDao eventParticipantDao;

    @Override
    public List<EventParticipant> getAllParticipants() {
        return eventParticipantDao
                .findAll()
                .stream()
                .map(EventParticipantModelConverter::convert)
                .collect(toList());
    }

    @Override
    public Optional<EventParticipant> getEventParticipant(long eventParticipantId) {
        return Optional
                .ofNullable(eventParticipantDao.getOne(eventParticipantId))
                .map(EventParticipantModelConverter::convert);
    }

    @Override
    public List<EventParticipant> getAllEventParticipants(long eventId) {
        return eventParticipantDao
                .getAllByEventId(eventId)
                .stream()
                .map(EventParticipantModelConverter::convert)
                .collect(toList());
    }

    @Override
    public Long createParticipant(EventParticipant eventParticipant) {
        return eventParticipantDao.save(EventParticipantDtoConverter.convert(eventParticipant)).getId();
    }

    @Override
    public void editParticipant(EventParticipant eventParticipant) {
        eventParticipantDao.save(EventParticipantDtoConverter.convert(eventParticipant));
    }

    @Override
    public void deleteParticipant(long eventParticipantId) {
        eventParticipantDao.delete(eventParticipantId);
    }
}
