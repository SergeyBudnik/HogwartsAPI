package com.bdev.hogwarts_api.data.dto.cabinet

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class NewCabinet @JsonCreator constructor(
        @JsonProperty("info") val info: CabinetInfo
)

data class ExistingCabinet @JsonCreator constructor(
        @JsonProperty("id") val id: Long,
        @JsonProperty("info") val info: CabinetInfo
)

data class CabinetInfo @JsonCreator constructor(
        @JsonProperty("name") val name: String
)