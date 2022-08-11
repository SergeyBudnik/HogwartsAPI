package com.bdev.hogwarts_api.data.dto.staff_member.week_status

import com.fasterxml.jackson.annotation.JsonProperty

data class StaffMemberWeekStatusId(
    @JsonProperty("staffMemberLogin") val staffMemberLogin: String,
    @JsonProperty("startTime") val startTime: Long,
    @JsonProperty("finishTime") val finishTime: Long
)
