package com.bdev.hogwarts_api.data.dto.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class StudentPayment {
    private Long id;
    @NonNull private Long studentId;
    @NonNull private Long amount;
    @NonNull private Long time;

    @JsonCreator
    public static StudentPayment create(
            @JsonProperty("id") Long id,
            @JsonProperty("studentId") Long studentId,
            @JsonProperty("amount") Long amount,
            @JsonProperty("time") Long time
    ) {
        return StudentPayment
                .builder()
                .id(id)
                .studentId(studentId)
                .amount(amount)
                .time(time)
                .build();
    }

}
