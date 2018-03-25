package com.bdev.hogwarts_api.data.dto.group;

import com.bdev.hogwarts_api.data.dto.EducationLevel;
import com.bdev.hogwarts_api.data.dto.Age;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Builder
@Data
public class Group {
    private Long id;
    @NonNull private String name;
    @NonNull private String bookName;
    @NonNull private Long cabinetId;
    @NonNull private List<GroupLesson> lessons;
    @NonNull private Age age;
    @NonNull private EducationLevel educationLevel;
    @NonNull private String color;

    @JsonCreator
    public static Group create(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("bookName") String bookName,
            @JsonProperty("cabinetId") Long cabinetId,
            @JsonProperty("lessons") List<GroupLesson> lessons,
            @JsonProperty("age") Age age,
            @JsonProperty("educationLevel") EducationLevel educationLevel,
            @JsonProperty("color") String color
    ) {
        return Group.builder()
                .id(id)
                .name(name)
                .bookName(bookName)
                .cabinetId(cabinetId)
                .lessons(lessons)
                .age(age)
                .educationLevel(educationLevel)
                .color(color)
                .build();
    }
}
