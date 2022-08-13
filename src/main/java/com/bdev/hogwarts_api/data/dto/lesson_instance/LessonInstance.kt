package com.bdev.hogwarts_api.data.dto.lesson_instance

import com.bdev.hogwarts_api.data.dto.group.GroupLesson
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class LessonInstance @JsonCreator constructor(
    @JsonProperty("lesson") val lesson: GroupLesson,
    @JsonProperty("status") val status: LessonInstanceStatus,
    @JsonProperty("startTime") val startTime: Long,
    @JsonProperty("finishTime") val finishTime: Long
)
