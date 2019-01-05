package com.bdev.hogwarts_api.service.event_participant

import com.bdev.hogwarts_api.data.dto.events.EventParticipant

interface EventParticipantService {
    fun getAllParticipants(): List<EventParticipant>
    fun getEventParticipant(eventParticipantId: Long): EventParticipant?
    fun getAllEventParticipants(eventId: Long): List<EventParticipant>
    fun createParticipant(eventParticipant: EventParticipant): Long?
    fun editParticipant(eventParticipant: EventParticipant)
    fun deleteParticipant(eventParticipantId: Long)
}
