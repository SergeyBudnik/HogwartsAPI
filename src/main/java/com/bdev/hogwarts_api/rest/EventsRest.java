package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.rest_service.event.EventsRestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@Api(tags = "Events", description = "PROTECTED")
public class EventsRest extends CommonRest {
    @Autowired
    private EventsRestService eventsRestService;

    @GetMapping("")
    public List<Event> getAll(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    ) {
        return eventsRestService.getAll(getUserInfo(authToken));
    }

    @GetMapping("/{id}")
    public Event getById(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long id
    ) {
        return eventsRestService.getById(getUserInfo(authToken), id);
    }

    @PostMapping("")
    public long create(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody Event event
    ) {
        return eventsRestService.create(getUserInfo(authToken), event);
    }

    @PutMapping("")
    public void update(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody Event event
    ) {
        eventsRestService.update(getUserInfo(authToken), event);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable Long id
    ) {
        eventsRestService.delete(getUserInfo(authToken), id);
    }
}
