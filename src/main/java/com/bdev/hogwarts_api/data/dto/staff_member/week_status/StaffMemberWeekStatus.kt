package com.bdev.hogwarts_api.data.dto.staff_member.week_status

import com.fasterxml.jackson.annotation.JsonProperty

data class StaffMemberWeekStatus(
    @JsonProperty("id") val id: StaffMemberWeekStatusId,
    @JsonProperty("type") val type: StaffMemberWeekStatusType
)
