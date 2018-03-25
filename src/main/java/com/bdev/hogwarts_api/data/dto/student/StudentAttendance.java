package com.bdev.hogwarts_api.data.dto.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class StudentAttendance {
    private Long id;
    @NonNull private Long studentId;
    @NonNull private StudentAttendanceType type;
    @NonNull private Long time;

    @JsonCreator
    public static StudentAttendance create(
       @JsonProperty("id") Long id,
       @JsonProperty("studentId") Long studentId,
       @JsonProperty("type") StudentAttendanceType type,
       @JsonProperty("time") Long time
    ) {
        return StudentAttendance
                .builder()
                .id(id)
                .studentId(studentId)
                .type(type)
                .time(time)
                .build();
    }
}
