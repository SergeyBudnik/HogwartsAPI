package com.bdev.hogwarts_api.data.dto.open_lesson

import com.bdev.hogwarts_api.data.dto.common.LessonTime
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Builder
import lombok.Getter
import lombok.NonNull

class OpenLesson(
    var id: Long? = null,
    var cabinetId: Long = 0,
    var teacherId: Long = 0,
    var startTime: LessonTime = LessonTime.T_07_00,
    var finishTime: LessonTime = LessonTime.T_07_00
)
