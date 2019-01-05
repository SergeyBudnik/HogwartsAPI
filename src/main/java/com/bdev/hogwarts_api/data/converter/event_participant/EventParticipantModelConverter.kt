package com.bdev.hogwarts_api.data.converter.event_participant

import com.bdev.hogwarts_api.data.dto.events.EventParticipant
import com.bdev.hogwarts_api.data.model.event_participant.EventParticipantModel
import com.bdev.hogwarts_api.utils.EncodingUtils

object EventParticipantModelConverter {
    fun convert(eventParticipantModel: EventParticipantModel): EventParticipant {
        return EventParticipant(
                eventParticipantModel.id,
                eventParticipantModel.eventId ?: throw RuntimeException(),
                EncodingUtils.fromBase64(eventParticipantModel.name ?: throw RuntimeException()),
                eventParticipantModel.status ?: throw RuntimeException(),
                eventParticipantModel.phone ?: throw RuntimeException(),
                eventParticipantModel.referralSource ?: throw RuntimeException(),
                eventParticipantModel.enlisted ?: throw RuntimeException()
        )
    }
}
