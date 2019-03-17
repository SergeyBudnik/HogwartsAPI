package com.bdev.hogwarts_api.service.events

import com.bdev.hogwarts_api.dao.EventDao
import com.bdev.hogwarts_api.data.converter.events.EventDtoConverter
import com.bdev.hogwarts_api.data.converter.events.EventModelConverter
import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.dto.events.EventType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.String.format
import java.util.*

@Service
open class EventServiceImpl : EventService {
    @Autowired
    private lateinit var eventDao: EventDao

    override fun getAll(): List<Event> {
        return eventDao
                .findAll()
                .map { EventModelConverter.convert(it) }
    }

    override fun getLatestEvent(eventType: EventType): Event? {
        return eventDao
                .getAllByEventTypeAndDateGreaterThan(eventType, Date().time)
                .minBy { it.date ?: Long.MAX_VALUE }
                ?.let { EventModelConverter.convert(it) }
    }

    override fun getAllFutureEvents(eventType: EventType): List<Event> {
        return eventDao
                .getAllByEventTypeAndDateGreaterThan(eventType, Date().time)
                .map { EventModelConverter.convert(it) }
    }

    override fun getById(id: Long): Event? {
        return eventDao.findOne(id)
                ?.let { EventModelConverter.convert(it) }
    }

    override fun exists(id: Long): Boolean {
        return eventDao.exists(id)
    }

    override fun create(speakingClub: Event): Long {
        if (speakingClub.id != null) {
            throw RuntimeException("Event should be null during creation")
        }

        return eventDao
                .save(EventDtoConverter.convert(speakingClub))
                .id ?: throw RuntimeException()
    }

    override fun update(speakingClub: Event) {
        if (!eventDao.exists(speakingClub.id)) {
            throw RuntimeException(format("Event with id '%d' does not exist", speakingClub.id))
        }

        eventDao.save(EventDtoConverter.convert(speakingClub))
    }

    override fun delete(id: Long) {
        if (!eventDao.exists(id)) {
            throw RuntimeException(format("Event with id '%d' does not exist", id))
        }

        eventDao.delete(id)
    }
}
