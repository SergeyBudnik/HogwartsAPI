package com.bdev.hogwarts_api.controllers_service

import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.dto.events.EventType
import com.bdev.hogwarts_api.service.events.EventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class EventsControllerServiceImpl : EventsControllerService {
    @Autowired
    private lateinit var eventService: EventService

    @Transactional(readOnly = true)
    override fun getCurrentEvent(eventType: EventType): Event? {
        return eventService.getLatestEvent(eventType)
    }
}
