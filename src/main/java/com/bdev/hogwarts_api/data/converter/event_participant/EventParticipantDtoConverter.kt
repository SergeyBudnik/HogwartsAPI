package com.bdev.hogwarts_api.data.converter.event_participant

import com.bdev.hogwarts_api.data.dto.events.EventParticipant
import com.bdev.hogwarts_api.data.model.event_participant.EventParticipantModel
import com.bdev.hogwarts_api.utils.EncodingUtils

object EventParticipantDtoConverter {
    fun convert(eventParticipant: EventParticipant): EventParticipantModel {
        val eventParticipantModel = EventParticipantModel()

        eventParticipantModel.id = eventParticipant.id
        eventParticipantModel.eventId = eventParticipant.eventId
        eventParticipantModel.name = EncodingUtils.toBase64(eventParticipant.name)
        eventParticipantModel.status = eventParticipant.status
        eventParticipantModel.phone = eventParticipant.phone
        eventParticipantModel.enlisted = eventParticipant.enlisted

        return eventParticipantModel
    }
}
