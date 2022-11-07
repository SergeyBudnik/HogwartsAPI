package com.bdev.hogwarts_api.data.dto.time

import com.fasterxml.jackson.annotation.JsonCreator

data class Week @JsonCreator constructor(
    val index: Int,
    val startTime: Long,
    val finishTime: Long
)
