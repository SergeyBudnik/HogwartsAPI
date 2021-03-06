package com.bdev.hogwarts_api.data.dto.student

import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.EducationLevel
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class StudentLegacy @JsonCreator constructor(
        @JsonProperty("id") val id: Long?,
        @JsonProperty("studentGroups") val studentGroups: List<StudentGroup>,
        @JsonProperty("name") val name: String,
        @JsonProperty("statusType") val statusType: StudentStatusType,
        @JsonProperty("phones") val phones: List<String>,
        @JsonProperty("emails") val emails: List<String>,
        @JsonProperty("vkLink") val vkLink: String,
        @JsonProperty("educationLevel") val educationLevel: EducationLevel,
        @JsonProperty("age") val age: Age
)
