package com.bdev.hogwarts_api.data.converter.person

import com.bdev.hogwarts_api.data.dto.person.*
import com.bdev.hogwarts_api.data.model.person.PersonContactModel
import com.bdev.hogwarts_api.data.model.person.PersonModel
import com.bdev.hogwarts_api.utils.EncodingUtils
import org.apache.commons.collections4.ListUtils

object PersonDtoConverter {
    fun convertNew(newPersonInfo: NewPersonInfo): PersonModel {
        return convert(id = null, person = newPersonInfo.person)
    }

    fun convertExisting(existingPersonInfo: ExistingPersonInfo): PersonModel {
        return convert(id = existingPersonInfo.id, person = existingPersonInfo.person)
    }

    private fun convert(id: Long?, person: Person): PersonModel {
        val personModel = PersonModel()

        personModel.id = id
        personModel.name = EncodingUtils.toBase64(person.name)
        personModel.contacts = ListUtils.union(
                person.contacts.phones.map { convert(it, PersonContactType.PHONE, personModel) },
                person.contacts.vkLinks.map { convert(it, PersonContactType.VK_LINK, personModel) }
        )

        return personModel
    }

    private fun convert(personContact: PersonContact, personContactType: PersonContactType, personModel: PersonModel): PersonContactModel {
        val personContactModel = PersonContactModel()

        personContactModel.person = personModel

        personContactModel.name = EncodingUtils.toBase64(personContact.name)
        personContactModel.value = EncodingUtils.toBase64(personContact.value)
        personContactModel.type = personContactType.id

        return personContactModel
    }
}
