package com.bdev.hogwarts_api.data.dto.group

import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.EducationLevel
import com.fasterxml.jackson.annotation.JsonProperty

class Group(
        @JsonProperty("id") val id: Long?,
        @JsonProperty("bookName") val bookName: String,
        @JsonProperty("type") val type: GroupType,
        @JsonProperty("cabinetId") val cabinetId: Long,
        @JsonProperty("managerId") val managerId: Long,
        @JsonProperty("lessons") val lessons: List<GroupLesson>,
        @JsonProperty("age") val age: Age,
        @JsonProperty("educationLevel") val educationLevel: EducationLevel,
        @JsonProperty("color") val color: String
)
