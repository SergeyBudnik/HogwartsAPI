package com.bdev.hogwarts_api.service.event_participant

import com.bdev.hogwarts_api.dao.EventParticipantDao
import com.bdev.hogwarts_api.data.converter.event_participant.EventParticipantDtoConverter
import com.bdev.hogwarts_api.data.converter.event_participant.EventParticipantModelConverter
import com.bdev.hogwarts_api.data.dto.events.EventParticipant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EventParticipantServiceImpl : EventParticipantService {
    @Autowired
    private lateinit var eventParticipantDao: EventParticipantDao

    override fun getAllParticipants(): List<EventParticipant> {
        return eventParticipantDao
                .findAll()
                .map { EventParticipantModelConverter.convert(it) }
    }

    override fun getEventParticipant(eventParticipantId: Long): EventParticipant? {
        return eventParticipantDao
                .getOne(eventParticipantId)
                ?.let { EventParticipantModelConverter.convert(it) }
    }

    override fun getAllEventParticipants(eventId: Long): List<EventParticipant> {
        return eventParticipantDao
                .getAllByEventId(eventId)
                .map { EventParticipantModelConverter.convert(it) }
    }

    override fun createParticipant(eventParticipant: EventParticipant): Long {
        return eventParticipantDao
                .save(EventParticipantDtoConverter.convert(eventParticipant))
                .id ?: throw RuntimeException()
    }

    override fun editParticipant(eventParticipant: EventParticipant) {
        eventParticipantDao
                .save(EventParticipantDtoConverter.convert(eventParticipant))
    }

    override fun deleteParticipant(eventParticipantId: Long) {
        eventParticipantDao
                .delete(eventParticipantId)
    }
}
