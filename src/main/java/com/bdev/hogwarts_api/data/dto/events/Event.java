package com.bdev.hogwarts_api.data.dto.events;

import com.bdev.hogwarts_api.data.dto.common.LessonTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class Event {
    private Long id;
    @NonNull private EventType eventType;
    @NonNull private String name;
    @NonNull private Long cabinetId;
    @NonNull private Long teacherId;
    @NonNull private Long date;
    @NonNull private LessonTime startTime;
    @NonNull private LessonTime finishTime;

    @JsonCreator
    public static Event create(
            @JsonProperty("id") Long id,
            @JsonProperty("eventType") EventType eventType,
            @JsonProperty("name") String name,
            @JsonProperty("cabinetId") Long cabinetId,
            @JsonProperty("teacherId") Long teacherId,
            @JsonProperty("date") Long date,
            @JsonProperty("startTime") LessonTime startTime,
            @JsonProperty("finishTime") LessonTime finishTime
    ) {
        return Event.builder()
                .id(id)
                .eventType(eventType)
                .name(name)
                .cabinetId(cabinetId)
                .teacherId(teacherId)
                .date(date)
                .startTime(startTime)
                .finishTime(finishTime)
                .build();
    }
}
