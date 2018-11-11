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
    @NonNull private String login;
    @NonNull private String name;
    @NonNull private TeacherType type;
    @NonNull private List<String> phones;
    @NonNull private List<String> emails;
    @NonNull private List<TeacherAvailability> availability;

    @JsonCreator
    public static Teacher create(
            @JsonProperty("id") Long id,
            @JsonProperty("login") String login,
            @JsonProperty("name") String name,
            @JsonProperty("type") TeacherType type,
            @JsonProperty("phones") List<String> phones,
            @JsonProperty("emails") List<String> emails,
            @JsonProperty("availability") List<TeacherAvailability> availability
    ) {
        return Teacher.builder()
                .id(id)
                .login(login)
                .name(name)
                .type(type)
                .phones(phones)
                .emails(emails)
                .availability(availability)
                .build();
    }
}
