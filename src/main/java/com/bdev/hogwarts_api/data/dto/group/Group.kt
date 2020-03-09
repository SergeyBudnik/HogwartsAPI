package com.bdev.hogwarts_api.data.dto.group

import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.EducationLevel
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Group @JsonCreator constructor(
        @JsonProperty("id") val id: Long?,
        @JsonProperty("type") val type: GroupType,
        @JsonProperty("cabinetId") val cabinetId: Long,
        @JsonProperty("headTeacherLogin") val headTeacherLogin: String,
        @JsonProperty("lessons") val lessons: List<GroupLesson>,
        @JsonProperty("age") val age: Age,
        @JsonProperty("educationLevel") val educationLevel: EducationLevel,
        @JsonProperty("color") val color: String
)
