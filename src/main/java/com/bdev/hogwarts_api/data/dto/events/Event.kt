package com.bdev.hogwarts_api.data.dto.events

import com.bdev.hogwarts_api.data.dto.common.LessonTime
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class Event @JsonCreator constructor(
        @JsonProperty("id")val id: Long? = null,
        @JsonProperty("eventType") val eventType: EventType,
        @JsonProperty("name") val name: String,
        @JsonProperty("cabinetId") val cabinetId: Long,
        @JsonProperty("teacherId") val teacherId: Long,
        @JsonProperty("date") val date: Long,
        @JsonProperty("startTime") val startTime: LessonTime,
        @JsonProperty("finishTime") val finishTime: LessonTime
)
