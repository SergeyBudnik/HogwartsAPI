package com.bdev.hogwarts_api.data.dto.student

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class StudentPayment @JsonCreator constructor(
        @JsonProperty("id") val id: Long?,
        @JsonProperty("studentId") val studentId: Long,
        @JsonProperty("teacherId") val teacherId: Long,
        @JsonProperty("amount") val amount: Long,
        @JsonProperty("time") val time: Long,
        @JsonProperty("processed") val processed: Boolean
)
