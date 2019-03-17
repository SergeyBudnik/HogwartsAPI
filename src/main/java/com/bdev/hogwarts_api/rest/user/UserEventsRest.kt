package com.bdev.hogwarts_api.rest.user

import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.dto.events.EventParticipant
import com.bdev.hogwarts_api.data.dto.events.EventParticipantStatus
import com.bdev.hogwarts_api.data.dto.events.EventType
import com.bdev.hogwarts_api.rest_service.public_events.PublicEventsRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/public/events")
@Api(tags = ["Public events"], description = "NOT-PROTECTED")
class UserEventsRest {
    @Autowired
    private lateinit var publicEventsRestService: PublicEventsRestService

    class Request(
            var name: String = "",
            var phone: String = ""
    )

    @GetMapping("/current/{eventType}")
    fun getCurrentEvent(
            @PathVariable eventType: EventType
    ): Event {
        return publicEventsRestService.getCurrentEvent(eventType)
    }

    @PostMapping("/{eventId}/participant")
    fun addEventParticipant(
            @PathVariable eventId: Long,
            @RequestBody request: Request
    ) {
        publicEventsRestService.addEventParticipant(
                EventParticipant(
                        eventId = eventId,
                        name = request.name,
                        status = EventParticipantStatus.REQUEST,
                        phone = request.phone,
                        enlisted = false
                )
        )
    }
}
