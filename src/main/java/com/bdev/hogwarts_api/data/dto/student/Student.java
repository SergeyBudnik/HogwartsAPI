package com.bdev.hogwarts_api.data.dto.student;

import com.bdev.hogwarts_api.data.dto.Age;
import com.bdev.hogwarts_api.data.dto.EducationLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Builder
@Getter
public class Student {
    private Long id;
    @NonNull private List<Long> groupIds;
    @NonNull private String name;
    @NonNull private StudentStatusType statusType;
    @NonNull private List<String> phones;
    @NonNull private List<String> emails;
    @NonNull private EducationLevel educationLevel;
    @NonNull private Age age;
    @NonNull private StudentReferralSource referralSource;

    @JsonCreator
    public static Student create(
        @JsonProperty("id") Long id,
        @JsonProperty("groupIds") List<Long> groupIds,
        @JsonProperty("name") String name,
        @JsonProperty("statusType") StudentStatusType statusType,
        @JsonProperty("emails") List<String> emails,
        @JsonProperty("phones") List<String> phones,
        @JsonProperty("age") Age age,
        @JsonProperty("educationLevel") EducationLevel educationLevel,
        @JsonProperty("referralSource") StudentReferralSource referralSource
    ) {
        return Student.builder()
                .id(id)
                .groupIds(groupIds)
                .name(name)
                .statusType(statusType)
                .phones(phones)
                .emails(emails)
                .educationLevel(educationLevel)
                .age(age)
                .referralSource(referralSource)
                .build();
    }
}
