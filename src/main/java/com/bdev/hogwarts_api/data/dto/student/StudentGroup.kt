package com.bdev.hogwarts_api.data.dto.student

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class StudentGroup @JsonCreator constructor(
        @JsonProperty("groupId") val groupId: Long,
        @JsonProperty("startTime") val startTime: Long,
        @JsonProperty("finishTime") val finishTime: Long?
)
