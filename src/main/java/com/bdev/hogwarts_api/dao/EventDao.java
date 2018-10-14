package com.bdev.hogwarts_api.dao;

import com.bdev.hogwarts_api.data.dto.events.EventType;
import com.bdev.hogwarts_api.data.model.events.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDao extends JpaRepository<EventModel, Long> {
    List<EventModel> getAllByEventTypeAndDateGreaterThan(EventType eventType, long after);
}
