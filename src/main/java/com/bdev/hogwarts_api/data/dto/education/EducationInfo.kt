package com.bdev.hogwarts_api.data.dto.education

import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.EducationLevel
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class EducationInfo @JsonCreator constructor(
        @JsonProperty("age") val age: Age,
        @JsonProperty("level") val level: EducationLevel
)