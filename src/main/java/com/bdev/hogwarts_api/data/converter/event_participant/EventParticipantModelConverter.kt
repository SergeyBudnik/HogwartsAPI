package com.bdev.hogwarts_api.data.converter.event_participant

import com.bdev.hogwarts_api.data.dto.events.EventParticipant
import com.bdev.hogwarts_api.data.model.event_participant.EventParticipantModel
import com.bdev.hogwarts_api.utils.EncodingUtils

object EventParticipantModelConverter {
    fun convert(eventParticipantModel: EventParticipantModel): EventParticipant {
        return EventParticipant(
                id = eventParticipantModel.id,
                eventId = eventParticipantModel.eventId ?: throw RuntimeException(),
                name = EncodingUtils.fromBase64(eventParticipantModel.name ?: throw RuntimeException()),
                status = eventParticipantModel.status ?: throw RuntimeException(),
                phone = eventParticipantModel.phone ?: throw RuntimeException(),
                enlisted = eventParticipantModel.enlisted ?: throw RuntimeException()
        )
    }
}
