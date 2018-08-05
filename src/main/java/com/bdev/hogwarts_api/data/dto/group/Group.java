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
    @NonNull private String bookName;
    @NonNull private GroupType type;
    @NonNull private Long cabinetId;
    @NonNull private Long managerId;
    @NonNull private List<GroupLesson> lessons;
    @NonNull private Age age;
    @NonNull private EducationLevel educationLevel;
    @NonNull private String color;

    @JsonCreator
    public static Group create(
            @JsonProperty("id") Long id,
            @JsonProperty("bookName") String bookName,
            @JsonProperty("type") GroupType type,
            @JsonProperty("cabinetId") Long cabinetId,
            @JsonProperty("managerId") Long managerId,
            @JsonProperty("lessons") List<GroupLesson> lessons,
            @JsonProperty("age") Age age,
            @JsonProperty("educationLevel") EducationLevel educationLevel,
            @JsonProperty("color") String color
    ) {
        return Group.builder()
                .id(id)
                .bookName(bookName)
                .type(type)
                .cabinetId(cabinetId)
                .managerId(managerId)
                .lessons(lessons)
                .age(age)
                .educationLevel(educationLevel)
                .color(color)
                .build();
    }
}
