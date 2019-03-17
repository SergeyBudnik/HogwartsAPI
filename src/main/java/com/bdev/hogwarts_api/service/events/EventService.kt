package com.bdev.hogwarts_api.service.events

import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.dto.events.EventType

interface EventService {
    fun getAll(): List<Event>
    fun getLatestEvent(eventType: EventType): Event?
    fun getAllFutureEvents(eventType: EventType): List<Event>
    fun getById(id: Long): Event?
    fun exists(id: Long): Boolean
    fun create(speakingClub: Event): Long
    fun update(speakingClub: Event)
    fun delete(id: Long)
}
