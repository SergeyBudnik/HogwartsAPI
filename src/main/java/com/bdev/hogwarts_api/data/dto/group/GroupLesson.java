package com.bdev.hogwarts_api.data.dto.group;

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek;
import com.bdev.hogwarts_api.data.dto.common.LessonTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class GroupLesson {
    private Long id;

    @NonNull private DayOfWeek day;
    @NonNull private LessonTime startTime;
    @NonNull private LessonTime finishTime;
    @NonNull private Long teacherId;

    @JsonCreator
    public static GroupLesson create(
            @JsonProperty("id") Long id,
            @JsonProperty("day") DayOfWeek day,
            @JsonProperty("startTime") LessonTime startTime,
            @JsonProperty("finishTime") LessonTime finishTime,
            @JsonProperty("teacherId") Long teacherId
    ) {
        return GroupLesson.builder()
                .id(id)
                .day(day)
                .startTime(startTime)
                .finishTime(finishTime)
                .teacherId(teacherId)
                .build();
    }
}
