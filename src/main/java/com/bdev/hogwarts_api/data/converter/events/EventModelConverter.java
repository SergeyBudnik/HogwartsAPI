package com.bdev.hogwarts_api.data.converter.events;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.model.events.EventModel;

public class EventModelConverter {
    public static Event convert(EventModel eventModel) {
        return Event
                .builder()
                .id(eventModel.getId())
                .cabinetId(eventModel.getCabinetId())
                .teacherId(eventModel.getTeacherId())
                .date(eventModel.getDate())
                .startTime(eventModel.getStartTime())
                .finishTime(eventModel.getFinishTime())
                .name(eventModel.getName())
                .build();
    }
}
