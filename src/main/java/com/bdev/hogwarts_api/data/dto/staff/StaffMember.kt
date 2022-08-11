package com.bdev.hogwarts_api.data.dto.staff

import com.bdev.hogwarts_api.data.dto.person.Person
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class StaffMember @JsonCreator constructor(
        @JsonProperty("login") val login: String,
        @JsonProperty("person") val person: Person,
        @JsonProperty("active") val active: Boolean,
        @JsonProperty("salaryIn30m") val salaryIn30m: Int,
        @JsonProperty("roles") val roles: StaffMemberRoles,
        @JsonProperty("subscriptions") val subscriptions: StaffMemberSubscriptions
)
