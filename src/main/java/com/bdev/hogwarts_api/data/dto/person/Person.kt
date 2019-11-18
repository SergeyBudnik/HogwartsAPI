package com.bdev.hogwarts_api.data.dto.person

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class NewPersonInfo @JsonCreator constructor(
        @JsonProperty("person") val person: Person
)

data class ExistingPersonInfo @JsonCreator constructor(
        @JsonProperty("id") val id: Long,
        @JsonProperty("person") val person: Person
)

data class Person @JsonCreator constructor(
        @JsonProperty("name") val name: String,
        @JsonProperty("contacts") val contacts: PersonContacts
)

data class PersonContacts @JsonCreator constructor(
        @JsonProperty("phones") val phones: List<PersonContact>,
        @JsonProperty("vkLinks") val vkLinks: List<PersonContact>
)

data class PersonContact @JsonCreator constructor(
        @JsonProperty("name") val name: String,
        @JsonProperty("value") val value: String
)

enum class PersonContactType(val id: String) {
    VK_LINK("VK_LINK"), PHONE("PHONE")
}