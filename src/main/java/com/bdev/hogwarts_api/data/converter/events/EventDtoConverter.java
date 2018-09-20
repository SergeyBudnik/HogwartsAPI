package com.bdev.hogwarts_api.data.converter.events;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.model.events.EventModel;

public class EventDtoConverter {
    public static EventModel convert(Event event) {
        EventModel eventModel = new EventModel();

        eventModel.setId(event.getId());
        eventModel.setTeacherId(event.getTeacherId());
        eventModel.setCabinetId(event.getCabinetId());
        eventModel.setDate(event.getDate());
        eventModel.setStartTime(event.getStartTime());
        eventModel.setFinishTime(event.getFinishTime());
        eventModel.setName(event.getName());

        return eventModel;
    }
}
