package com.bdev.hogwarts_api.data.dto.group

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.common.LessonTime
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class GroupLesson @JsonCreator constructor(
        @JsonProperty("id") val id: Long?,
        @JsonProperty("day") val day: DayOfWeek,
        @JsonProperty("startTime") val startTime: LessonTime,
        @JsonProperty("finishTime") val finishTime: LessonTime,
        @JsonProperty("teacherLogin") val teacherLogin: String,
        @JsonProperty("creationTime") val creationTime: Long,
        @JsonProperty("deactivationTime") val deactivationTime: Long
)
