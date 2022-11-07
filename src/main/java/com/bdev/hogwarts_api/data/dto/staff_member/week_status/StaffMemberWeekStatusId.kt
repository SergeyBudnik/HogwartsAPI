package com.bdev.hogwarts_api.data.dto.staff_member.week_status

import com.bdev.hogwarts_api.data.dto.time.Month
import com.fasterxml.jackson.annotation.JsonProperty

data class StaffMemberWeekStatusId(
    @JsonProperty("staffMemberLogin") val staffMemberLogin: String,
    @JsonProperty("weekIndex") val weekIndex: Int,
    @JsonProperty("month") val month: Month,
    @JsonProperty("year") val year: Int
)
