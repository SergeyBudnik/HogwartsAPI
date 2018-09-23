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

    @RequestMapping("/{eventType}/{platform}")
    public ModelAndView open(
            @PathVariable EventType eventType,
            @PathVariable Platform platform
    ) {
        Event event = eventsControllerService.getCurrentEvent(eventType);

        return new ModelAndView(
                "event/" + platform.getPath() + "/event", "event",
                event
        );
    }
}
