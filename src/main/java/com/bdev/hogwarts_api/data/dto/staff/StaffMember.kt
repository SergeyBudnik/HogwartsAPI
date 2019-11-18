package com.bdev.hogwarts_api.data.dto.staff

import com.bdev.hogwarts_api.data.dto.person.Person
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class NewStaffMemberInfo @JsonCreator constructor(
        @JsonProperty("person") val person: Person,
        @JsonProperty("staffMember") val staffMember: StaffMember
)

data class ExistingStaffMemberInfo @JsonCreator constructor(
        @JsonProperty("person") val person: Person,
        @JsonProperty("staffMember") val staffMember: StaffMember
)

data class StaffMember @JsonCreator constructor(
        @JsonProperty("login") val login: String
)