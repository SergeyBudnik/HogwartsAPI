package com.bdev.hogwarts_api.data.dto.lesson

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class LessonTransfer @JsonCreator constructor(
        @JsonProperty("id") val id: Long?,
        @JsonProperty("lessonId") val lessonId: Long,
        @JsonProperty("teacherId") val teacherId: Long,
        @JsonProperty("fromTime") val fromTime: Long,
        @JsonProperty("toTime") val toTime: Long
)
