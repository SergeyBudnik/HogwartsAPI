package com.bdev.hogwarts_api.data.dto.group

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class GroupAndLesson @JsonCreator constructor(
    @JsonProperty("group") val group: Group,
    @JsonProperty("lesson") val lesson: GroupLesson
)
