package com.bdev.hogwarts_api.data.dto.teacher;

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek;
import com.bdev.hogwarts_api.data.dto.common.LessonTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class TeacherAvailability {
    private Long id;
    @NonNull private DayOfWeek dayOfWeek;
    @NonNull private LessonTime startTime;
    @NonNull private LessonTime finishTime;
}
