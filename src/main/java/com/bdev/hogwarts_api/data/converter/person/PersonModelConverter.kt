package com.bdev.hogwarts_api.data.converter.person

import com.bdev.hogwarts_api.data.dto.person.*
import com.bdev.hogwarts_api.data.model.person.PersonContactModel
import com.bdev.hogwarts_api.data.model.person.PersonModel
import com.bdev.hogwarts_api.utils.EncodingUtils

object PersonModelConverter {
    fun convert(personModel: PersonModel): ExistingPersonInfo {
        return ExistingPersonInfo(
                id = personModel.id ?: throw RuntimeException(),
                person = Person(
                        name = EncodingUtils.fromBase64(personModel.name),
                        contacts = PersonContacts(
                                phones = convert(personModel.contacts, PersonContactType.PHONE),
                                vkLinks = convert(personModel.contacts, PersonContactType.VK_LINK)
                        )
                )
        )
    }

    fun convert(personContactModels: List<PersonContactModel>, personContactType: PersonContactType): List<PersonContact> {
        return personContactModels.filter { it.type == personContactType.id }.map {
            PersonContact(
                    name = EncodingUtils.fromBase64(it.name),
                    value = EncodingUtils.fromBase64(it.value)
            )
        }
    }
}
