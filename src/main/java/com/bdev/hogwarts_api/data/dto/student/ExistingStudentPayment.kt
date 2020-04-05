package com.bdev.hogwarts_api.data.dto.student

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class NewStudentPayment @JsonCreator constructor(
        @JsonProperty("info") val info: StudentPaymentInfo
)

data class ExistingStudentPayment @JsonCreator constructor(
        @JsonProperty("id") val id: Long?,
        @JsonProperty("info") val info: StudentPaymentInfo,
        @JsonProperty("processed") val processed: Boolean
)

data class StudentPaymentInfo @JsonCreator constructor(
        @JsonProperty("studentLogin") val studentLogin: String,
        @JsonProperty("staffMemberLogin") val staffMemberLogin: String,
        @JsonProperty("amount") val amount: Long,
        @JsonProperty("time") val time: Long
)
