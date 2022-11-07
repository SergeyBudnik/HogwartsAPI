package com.bdev.hogwarts_api.data.dto.staff_member.action

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class StaffMemberAction @JsonCreator constructor(
    @JsonProperty("staffMemberLogin") val staffMemberLogin: String,
    @JsonProperty("startTime") val startTime: Long,
    @JsonProperty("finishTime") val finishTime: Long,
    @JsonProperty("type") val type: StaffMemberActionType,
    @JsonProperty("price") val price: Int,
    @JsonProperty("status") val status: StaffMemberActionStatus
)
