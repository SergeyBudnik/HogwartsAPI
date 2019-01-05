package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.event_participant.EventParticipantModel
import org.springframework.data.jpa.repository.JpaRepository

interface EventParticipantDao : JpaRepository<EventParticipantModel, Long> {
    fun getAllByEventId(eventId: Long): List<EventParticipantModel>
}
