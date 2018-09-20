package com.bdev.hogwarts_api.dao;

import com.bdev.hogwarts_api.data.model.events.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDao extends JpaRepository<EventModel, Long> {
}
