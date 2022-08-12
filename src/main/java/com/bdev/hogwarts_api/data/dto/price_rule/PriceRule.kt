package com.bdev.hogwarts_api.data.dto.price_rule

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class PriceRule @JsonCreator constructor(
    @JsonProperty("id") val id: PriceRuleId,
    @JsonProperty("priceFor30m") val priceFor30m: Int
)
