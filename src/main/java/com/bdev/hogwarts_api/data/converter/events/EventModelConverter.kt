package com.bdev.hogwarts_api.data.converter.events

import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.model.events.EventModel

import com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64

object EventModelConverter {
    fun convert(eventModel: EventModel): Event {
        return Event(
                eventModel.id,
                eventModel.eventType ?: throw RuntimeException(),
                fromBase64(eventModel.name ?: throw RuntimeException()),
                eventModel.cabinetId ?: throw RuntimeException(),
                eventModel.teacherId ?: throw RuntimeException(),
                eventModel.date ?: throw RuntimeException(),
                eventModel.startTime ?: throw RuntimeException(),
                eventModel.finishTime ?: throw RuntimeException()
        )
    }
}
