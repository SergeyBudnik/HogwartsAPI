package com.bdev.hogwarts_api.data.dto.cabinet

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Cabinet @JsonCreator constructor(
        @JsonProperty("id") val id: Long?,
        @JsonProperty("name") var name: String
)
