package com.bdev.hogwarts_api.data.dto.student.studying

import com.bdev.hogwarts_api.data.dto.education.EducationInfo
import com.bdev.hogwarts_api.data.dto.person.Person
import com.bdev.hogwarts_api.data.dto.student.StudentGroup
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Student @JsonCreator constructor(
        @JsonProperty("login") val login: String,
        @JsonProperty("person") val person: Person,
        @JsonProperty("educationInfo") val educationInfo: EducationInfo,
        @JsonProperty("studentGroups") val studentGroups: List<StudentGroup>,
        @JsonProperty("statusType") val statusType: StudentStatusType
)
