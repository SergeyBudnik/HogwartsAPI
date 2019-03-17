package com.bdev.hogwarts_api.controllers

import com.bdev.hogwarts_api.controllers_service.EventsControllerService
import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.dto.events.EventType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import springfox.documentation.annotations.ApiIgnore
import java.util.*
import kotlin.collections.LinkedHashMap

@ApiIgnore
@Controller
open class EventsController {
    @Autowired
    private lateinit var eventsControllerService: EventsControllerService

    enum class Platform constructor(val path: String) {
        DESKTOP("desktop"), MOBILE("mobile")
    }

    @RequestMapping("/pages/test-date-picker/{platform}")
    fun openTestDatePicker(
            @PathVariable platform: Platform
    ): ModelAndView {
        val allFutureEventsByDayOfWeek = LinkedHashMap<DayOfWeek, MutableList<Event>>()

        val allFutureEvents = eventsControllerService.getClosesFutureEvents(EventType.TEST_LESSON, 5)

        allFutureEvents.forEach { event ->
            val eventDayOfWeek = getEventDayOfWeek(event)

            val events = allFutureEventsByDayOfWeek[eventDayOfWeek] ?: ArrayList()

            events.add(event)

            allFutureEventsByDayOfWeek[eventDayOfWeek] = events
        }

        return ModelAndView("test-date-picker/" + platform.path, "events", allFutureEventsByDayOfWeek)
    }

    private fun getEventDayOfWeek(event: Event): DayOfWeek {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+3:00"))

        calendar.time = Date(event.date)

        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> DayOfWeek.SUNDAY
            2 -> DayOfWeek.MONDAY
            3 -> DayOfWeek.TUESDAY
            4 -> DayOfWeek.WEDNESDAY
            5 -> DayOfWeek.THURSDAY
            6 -> DayOfWeek.FRIDAY
            7 -> DayOfWeek.SATURDAY
            else -> DayOfWeek.SATURDAY
        }
    }

    @RequestMapping("/pages/events/{eventType}/{platform}")
    open fun openClosestEvent(
            @PathVariable eventType: EventType,
            @PathVariable platform: Platform
    ): ModelAndView {
        val currentEvent = eventsControllerService.getCurrentEvent(eventType)

        return if (currentEvent == null) {
            getNoEventModel(platform)
        } else {
            getEventModel(platform, currentEvent)
        }
    }

    @RequestMapping("/pages/events/specific/{eventId}/{platform}")
    open fun openSpecificEvent(
            @PathVariable eventId: Long,
            @PathVariable platform: Platform
    ): ModelAndView {
        val event = eventsControllerService.getEvent(eventId)

        return if (event == null) {
            getNoEventModel(platform)
        } else {
            getEventModel(platform, event)
        }
    }

    private fun getEventModel(platform: Platform, event: Event): ModelAndView {
        return ModelAndView("event/" + platform.path, "event", event)
    }

    private fun getNoEventModel(platform: Platform): ModelAndView {
        return ModelAndView("no-event/" + platform.path)
    }
}
