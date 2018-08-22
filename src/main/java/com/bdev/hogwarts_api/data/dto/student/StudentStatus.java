package com.bdev.hogwarts_api.data.dto.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class StudentStatus {
    private Long id;
    @NonNull private Long studentId;
    @NonNull private StudentStatusType status;
    @NonNull private Long creationTime;
    @NonNull private Long actionTime;

    @JsonCreator
    public static StudentStatus create(
            @JsonProperty("id") Long id,
            @JsonProperty("studentId") Long studentId,
            @JsonProperty("status") StudentStatusType status,
            @JsonProperty("creationTime") Long creationTime,
            @JsonProperty("actionTime") Long actionTime
    ) {
        return StudentStatus.builder()
                .id(id)
                .studentId(studentId)
                .status(status)
                .creationTime(creationTime)
                .actionTime(actionTime)
                .build();
    }
}
