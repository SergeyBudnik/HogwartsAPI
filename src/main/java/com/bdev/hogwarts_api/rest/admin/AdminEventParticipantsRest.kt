package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.events.EventParticipant
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.event_participants.EventParticipantsRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/event-participants")
@Api(tags = ["Event Participants"], description = "PROTECTED")
class AdminEventParticipantsRest : CommonRest() {
    @Autowired
    private lateinit var eventParticipantsRestService: EventParticipantsRestService

    @GetMapping("")
    fun getAllParticipants(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<EventParticipant> {
        return eventParticipantsRestService.getAllParticipants(
                getUserInfo(authToken)
        )
    }

    @GetMapping("/{eventParticipantId}")
    fun getEventParticipant(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable eventParticipantId: Long
    ): EventParticipant {
        return eventParticipantsRestService.getEventParticipant(
                getUserInfo(authToken),
                eventParticipantId
        )
    }

    @GetMapping("/event/{eventId}")
    fun getEventParticipants(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable eventId: Long
    ): List<EventParticipant> {
        return eventParticipantsRestService.getEventParticipants(
                getUserInfo(authToken),
                eventId
        )
    }

    @PostMapping("")
    fun createEventParticipant(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody eventParticipant: EventParticipant
    ): Long {
        return eventParticipantsRestService.createEventParticipant(
                getUserInfo(authToken),
                eventParticipant
        )
    }

    @PutMapping("")
    fun editEventParticipant(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody eventParticipant: EventParticipant
    ) {
        eventParticipantsRestService.editEventParticipant(
                getUserInfo(authToken),
                eventParticipant
        )
    }

    @DeleteMapping("/{eventParticipantId}")
    fun deleteEventParticipant(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable eventParticipantId: Long
    ) {
        eventParticipantsRestService.deleteEventParticipant(
                getUserInfo(authToken),
                eventParticipantId
        )
    }
}
