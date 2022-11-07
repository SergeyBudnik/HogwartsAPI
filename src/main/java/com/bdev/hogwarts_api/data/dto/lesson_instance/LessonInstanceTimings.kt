package com.bdev.hogwarts_api.data.dto.lesson_instance

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.time.Month
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class LessonInstanceTimings @JsonCreator constructor(
    @JsonProperty("dayOfWeek") val dayOfWeek: DayOfWeek,
    @JsonProperty("weekIndex") val weekIndex: Int,
    @JsonProperty("month") val month: Month,
    @JsonProperty("year") val year: Int
)
