package com.bdev.hogwarts_api.controllers_service

import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.dto.events.EventType

interface EventsControllerService {
    fun getCurrentEvent(eventType: EventType): Event?
}
