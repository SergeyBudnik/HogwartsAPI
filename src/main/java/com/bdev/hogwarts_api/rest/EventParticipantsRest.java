package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.events.EventParticipant;
import com.bdev.hogwarts_api.rest_service.event_participants.EventParticipantsRestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-participants")
@Api(tags = "Event Participants", description = "PROTECTED")
public class EventParticipantsRest extends CommonRest {
    @Autowired
    private EventParticipantsRestService eventParticipantsRestService;

    @GetMapping("")
    public List<EventParticipant> getAllParticipants(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    ) {
        return eventParticipantsRestService.getAllParticipants(
                getUserInfo(authToken)
        );
    }

    @GetMapping("/{eventParticipantId}")
    public EventParticipant getEventParticipant(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long eventParticipantId
    ) {
        return eventParticipantsRestService.getEventParticipant(
                getUserInfo(authToken),
                eventParticipantId
        );
    }

    @GetMapping("/event/{eventId}")
    public List<EventParticipant> getEventParticipants(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long eventId
    ) {
        return eventParticipantsRestService.getEventParticipants(
                getUserInfo(authToken),
                eventId
        );
    }

    @PostMapping("")
    public long createEventParticipant(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody EventParticipant eventParticipant
    ) {
        return eventParticipantsRestService.createEventParticipant(
                getUserInfo(authToken),
                eventParticipant
        );
    }

    @PutMapping("")
    public void editEventParticipant(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody EventParticipant eventParticipant
    ) {
        eventParticipantsRestService.editEventParticipant(
                getUserInfo(authToken),
                eventParticipant
        );
    }

    @DeleteMapping("/{eventParticipantId}")
    public void deleteEventParticipant(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long eventParticipantId
    ) {
        eventParticipantsRestService.deleteEventParticipant(
                getUserInfo(authToken),
                eventParticipantId
        );
    }
}
