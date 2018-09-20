package com.bdev.hogwarts_api.data.dto.open_lesson;

import com.bdev.hogwarts_api.data.dto.common.LessonTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class OpenLesson {
    private Long id;
    @NonNull private Long cabinetId;
    @NonNull private Long teacherId;
    @NonNull private LessonTime startTime;
    @NonNull private LessonTime finishTime;

    @JsonCreator
    public static OpenLesson create(
            @JsonProperty("id") Long id,
            @JsonProperty("cabinetId") Long cabinetId,
            @JsonProperty("teacherId") Long teacherId,
            @JsonProperty("startTime") LessonTime startTime,
            @JsonProperty("finishTime") LessonTime finishTime
    ) {
        return OpenLesson.builder()
                .id(id)
                .cabinetId(cabinetId)
                .teacherId(teacherId)
                .startTime(startTime)
                .finishTime(finishTime)
                .build();
    }
}
