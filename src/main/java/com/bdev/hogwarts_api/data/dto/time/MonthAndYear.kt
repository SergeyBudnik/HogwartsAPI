package com.bdev.hogwarts_api.data.dto.time

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class MonthAndYear @JsonCreator constructor(
    @JsonProperty("month") val month: Month,
    @JsonProperty("year") val year: Int
)
