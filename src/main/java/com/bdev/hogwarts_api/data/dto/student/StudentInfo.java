package com.bdev.hogwarts_api.data.dto.student;

import com.bdev.hogwarts_api.data.dto.Age;
import com.bdev.hogwarts_api.data.dto.EducationLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Builder
@Getter
public class StudentInfo {
    @NonNull private String name;
    @NonNull private List<String> telephones;
    @NonNull private List<String> emails;
    @NonNull private EducationLevel level;
    @NonNull private Age age;
    @NonNull private StudentReferralSource referralSource;
}
