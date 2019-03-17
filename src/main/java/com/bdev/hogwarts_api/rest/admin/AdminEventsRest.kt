package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.event.EventsRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/events")
@Api(tags = ["Events"], description = "PROTECTED")
class AdminEventsRest : CommonRest() {
    @Autowired
    private lateinit var eventsRestService: EventsRestService

    @PostMapping("/generate-week-tests")
    fun generateWeekTests(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ) {
        eventsRestService.generateWeekTests(getUserInfo(authToken))
    }

    @GetMapping("")
    fun getAll(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<Event> {
        return eventsRestService.getAll(getUserInfo(authToken))
    }

    @GetMapping("/{id}")
    fun getById(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: Long
    ): Event {
        return eventsRestService.getById(getUserInfo(authToken), id)
    }

    @PostMapping("")
    fun create(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody event: Event
    ): Long {
        return eventsRestService.create(getUserInfo(authToken), event)
    }

    @PutMapping("")
    fun update(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody event: Event
    ) {
        eventsRestService.update(getUserInfo(authToken), event)
    }

    @DeleteMapping("/{id}")
    fun delete(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: Long?
    ) {
        eventsRestService.delete(getUserInfo(authToken), id!!)
    }
}
