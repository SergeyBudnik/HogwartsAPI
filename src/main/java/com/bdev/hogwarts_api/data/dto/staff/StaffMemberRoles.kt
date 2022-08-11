package com.bdev.hogwarts_api.data.dto.staff

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class StaffMemberRoles @JsonCreator constructor(
        @JsonProperty("teacher") val teacher: Boolean
)
