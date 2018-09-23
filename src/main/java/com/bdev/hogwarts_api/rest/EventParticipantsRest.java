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

    @GetMapping("/{eventId}")
    public List<EventParticipant> getEventParticipants(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long eventId
    ) {
        return eventParticipantsRestService.getEventParticipants(
                getUserInfo(authToken),
                eventId
        );
    }
}
