package com.bdev.hogwarts_api.dao;

import com.bdev.hogwarts_api.data.model.event_participant.EventParticipantModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventParticipantDao extends JpaRepository<EventParticipantModel, Long> {
    List<EventParticipantModel> getAllByEventId(long eventId);
}
