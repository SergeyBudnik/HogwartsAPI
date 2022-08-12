package com.bdev.hogwarts_api.data.dto.price_rule

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class PriceRuleId @JsonCreator constructor(
    @JsonProperty("lessonTypeId") val lessonTypeId: String,
    @JsonProperty("startTime") val startTime: Long,
    @JsonProperty("finishTime") val finishTime: Long
)
