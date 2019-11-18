package com.bdev.hogwarts_api.service.person

import com.bdev.hogwarts_api.dao.PersonDao
import com.bdev.hogwarts_api.data.converter.person.PersonDtoConverter
import com.bdev.hogwarts_api.data.converter.person.PersonModelConverter
import com.bdev.hogwarts_api.data.dto.person.ExistingPersonInfo
import com.bdev.hogwarts_api.data.dto.person.NewPersonInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface PersonStorageService {
    fun getAllPersons(): List<ExistingPersonInfo>
    fun getPerson(id: Long): ExistingPersonInfo?
    fun createPerson(person: NewPersonInfo): Long
    fun updatePerson(person: ExistingPersonInfo)
    fun deletePerson(id: Long)
}

@Service
open class PersonStorageServiceImpl @Autowired constructor(
        private val personDao: PersonDao
) : PersonStorageService {
    override fun getAllPersons(): List<ExistingPersonInfo> {
        return personDao.findAll().map { PersonModelConverter.convert(it) }
    }

    override fun getPerson(id: Long): ExistingPersonInfo? {
        return personDao.findOne(id)?.let { PersonModelConverter.convert(it) }
    }

    override fun createPerson(person: NewPersonInfo): Long {
        return personDao.save(PersonDtoConverter.convertNew(person)).id ?: throw RuntimeException()
    }

    override fun updatePerson(person: ExistingPersonInfo) {
        personDao.save(PersonDtoConverter.convertExisting(person))
    }

    override fun deletePerson(id: Long) {
        personDao.delete(id)
    }
}
