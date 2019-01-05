package com.bdev.hogwarts_api.data.converter.events

import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.model.events.EventModel

import com.bdev.hogwarts_api.utils.EncodingUtils.toBase64

object EventDtoConverter {
    fun convert(event: Event): EventModel {
        val eventModel = EventModel()

        eventModel.id = event.id
        eventModel.eventType = event.eventType
        eventModel.name = toBase64(event.name)
        eventModel.teacherId = event.teacherId
        eventModel.cabinetId = event.cabinetId
        eventModel.date = event.date
        eventModel.startTime = event.startTime
        eventModel.finishTime = event.finishTime

        return eventModel
    }
}
