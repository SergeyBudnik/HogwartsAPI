package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.dto.events.EventType
import com.bdev.hogwarts_api.data.model.events.EventModel
import org.springframework.data.jpa.repository.JpaRepository

interface EventDao : JpaRepository<EventModel, Long> {
    fun getAllByEventTypeAndDateGreaterThan(eventType: EventType, after: Long): List<EventModel>
}
