package com.bdev.hogwarts_api.data.dto.teacher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Builder
@Getter
public class Teacher {
    private Long id;
    @NonNull private String name;
    @NonNull private TeacherType type;
    @NonNull private List<String> phones;
    @NonNull private List<String> emails;

    @JsonCreator
    public static Teacher create(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("type") TeacherType type,
            @JsonProperty("phones") List<String> phones,
            @JsonProperty("emails") List<String> emails
    ) {
        return Teacher.builder()
                .id(id)
                .name(name)
                .type(type)
                .phones(phones)
                .emails(emails)
                .build();
    }
}
