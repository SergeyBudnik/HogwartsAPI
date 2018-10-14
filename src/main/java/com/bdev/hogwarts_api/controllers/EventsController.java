package com.bdev.hogwarts_api.controllers;

import com.bdev.hogwarts_api.controllers_service.EventsControllerService;
import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.dto.events.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@ApiIgnore
@Controller
public class EventsController {
    @AllArgsConstructor
    @Getter
    public enum Platform {
        DESKTOP("desktop"), MOBILE("mobile");

        private String path;
    }

    @Autowired
    private EventsControllerService eventsControllerService;

    @RequestMapping("/pages/events/{eventType}/{platform}")
    public ModelAndView open(
            @PathVariable EventType eventType,
            @PathVariable Platform platform
    ) {
        Optional<Event> eventOptional = eventsControllerService.getCurrentEvent(eventType);

        return eventOptional
            .map(event -> getEventModel(platform, event))
            .orElseGet(() -> getNoEventModel(platform));
    }

    private ModelAndView getEventModel(Platform platform, Event event) {
        return new ModelAndView("event/" + platform.getPath(), "event", event);
    }

    private ModelAndView getNoEventModel(Platform platform) {
        return new ModelAndView("no-event/" + platform.getPath());
    }
}
