package com.bdev.hogwarts_api.data.converter.events;

import com.bdev.hogwarts_api.data.dto.events.Event;
import com.bdev.hogwarts_api.data.model.events.EventModel;

import static com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64;

public class EventModelConverter {
    public static Event convert(EventModel eventModel) {
        return Event
                .builder()
                .id(eventModel.getId())
                .eventType(eventModel.getEventType())
                .name(fromBase64(eventModel.getName()))
                .cabinetId(eventModel.getCabinetId())
                .teacherId(eventModel.getTeacherId())
                .date(eventModel.getDate())
                .startTime(eventModel.getStartTime())
                .finishTime(eventModel.getFinishTime())
                .build();
    }
}
