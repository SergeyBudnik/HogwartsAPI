package com.bdev.hogwarts_api.data.dto.teacher;

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek;
import com.bdev.hogwarts_api.data.dto.common.LessonTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class TeacherAvailability {
    @NonNull private DayOfWeek dayOfWeek;
    @NonNull private LessonTime time;

    @JsonCreator
    public static TeacherAvailability create(
            @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
            @JsonProperty("time") LessonTime time
    ) {
        return TeacherAvailability
                .builder()
                .dayOfWeek(dayOfWeek)
                .time(time)
                .build();
    }
}
