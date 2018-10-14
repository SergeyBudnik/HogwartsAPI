package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.dto.events.EventParticipant;
import com.bdev.hogwarts_api.data.dto.events.EventParticipantStatus;
import com.bdev.hogwarts_api.data.dto.events.EventType;
import com.bdev.hogwarts_api.data.dto.student.StudentReferralSource;
import com.bdev.hogwarts_api.rest_service.public_events.PublicEventsRestService;
import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/events")
@Api(tags = "Public events", description = "NOT-PROTECTED")
public class PublicEventsRest {
    @Data
    private static class Request {
        private String name;
        private String phone;
        private StudentReferralSource referralSource;
    }

    @Autowired
    private PublicEventsRestService publicEventsRestService;

    @GetMapping("/current/{eventType}")
    public Event getCurrentEvent(
            @PathVariable EventType eventType
    ) {
        return publicEventsRestService.getCurrentEvent(eventType);
    }

    @PostMapping("/{eventId}/participant")
    public void addEventParticipant(
            @PathVariable long eventId,
            @RequestBody Request request
    ) {
        publicEventsRestService.addEventParticipant(
                EventParticipant
                        .builder()
                        .eventId(eventId)
                        .name(request.name)
                        .status(EventParticipantStatus.REQUEST)
                        .phone(request.phone)
                        .referralSource(request.referralSource)
                        .build()
        );
    }
}
