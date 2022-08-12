package com.bdev.hogwarts_api.data.dto.lesson_type

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class LessonType @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String
)
