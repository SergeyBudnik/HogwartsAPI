package com.bdev.hogwarts_api.controllers

import com.bdev.hogwarts_api.controllers_service.EventsControllerService
import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.dto.events.EventType
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import springfox.documentation.annotations.ApiIgnore

import java.util.Optional

@ApiIgnore
@Controller
open class EventsController {
    @Autowired
    private lateinit var eventsControllerService: EventsControllerService

    @AllArgsConstructor
    @Getter
    enum class Platform constructor(val path: String) {
        DESKTOP("desktop"), MOBILE("mobile")
    }

    @RequestMapping("/pages/events/{eventType}/{platform}")
    open fun open(
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

    private fun getEventModel(platform: Platform, event: Event): ModelAndView {
        return ModelAndView("event/" + platform.path, "event", event)
    }

    private fun getNoEventModel(platform: Platform): ModelAndView {
        return ModelAndView("no-event/" + platform.path)
    }
}
