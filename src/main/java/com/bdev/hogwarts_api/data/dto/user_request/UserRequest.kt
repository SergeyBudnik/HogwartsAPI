package com.bdev.hogwarts_api.data.dto.user_request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class UserRequest @JsonCreator constructor(
        @JsonProperty("id") val id: Long?,
        @JsonProperty("name") val name: String,
        @JsonProperty("phone") val phone: String,
        @JsonProperty("date") val date: Long
)
