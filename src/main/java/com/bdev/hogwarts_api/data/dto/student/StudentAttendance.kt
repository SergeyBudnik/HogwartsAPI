package com.bdev.hogwarts_api.data.dto.student

import com.bdev.hogwarts_api.data.dto.group.GroupType
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class StudentAttendanceId @JsonCreator constructor(
        @JsonProperty("studentLogin") val studentLogin: String,
        @JsonProperty("startTime") val startTime: Long,
        @JsonProperty("finishTime") val finishTime: Long
)

data class StudentAttendance @JsonCreator constructor(
        @JsonProperty("studentLogin") val studentLogin: String,
        @JsonProperty("type") val type: StudentAttendanceType,
        @JsonProperty("groupType") val groupType: GroupType,
        @JsonProperty("studentsInGroup") val studentsInGroup: Int,
        @JsonProperty("startTime") val startTime: Long,
        @JsonProperty("finishTime") val finishTime: Long,
        @JsonProperty("ignoreSingleStudentPricing") val ignoreSingleStudentPricing: Boolean
)
