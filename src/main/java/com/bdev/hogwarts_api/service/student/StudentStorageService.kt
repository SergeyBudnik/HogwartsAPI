package com.bdev.hogwarts_api.service.student

import com.bdev.hogwarts_api.dao.StudentDao
import com.bdev.hogwarts_api.data.converter.student.StudentDtoConverter
import com.bdev.hogwarts_api.data.converter.student.StudentModelConverter
import com.bdev.hogwarts_api.data.dto.person.ExistingPersonInfo
import com.bdev.hogwarts_api.data.dto.person.NewPersonInfo
import com.bdev.hogwarts_api.data.dto.student.studying.Student
import com.bdev.hogwarts_api.service.common.CommonStorageService
import com.bdev.hogwarts_api.service.person.PersonStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface StudentStorageService : CommonStorageService<String, Student, Student>

@Service
open class StudentStorageServiceImpl @Autowired constructor(
        private val studentDao: StudentDao,
        private val personStorageService: PersonStorageService
): StudentStorageService {
    override fun getAll(): List<Student> {
        return studentDao
                .findAll()
                .mapNotNull {
                    personStorageService.withExistingPerson(it.personId) { person ->
                        StudentModelConverter.convert(it, person.person)
                    }
                }
    }

    override fun getById(id: String): Student? {
        val studentModel = studentDao.findOne(id)

        return if (studentModel == null) {
            null
        } else {
            return personStorageService.withExistingPerson(studentModel.personId) { person ->
                StudentModelConverter.convert(
                        studentModel = studentModel,
                        person = person.person
                )
            }
        }
    }

    override fun create(o: Student): String {
        val personId = personStorageService.createPerson(
                NewPersonInfo(o.person)
        )

        studentDao.save(
                StudentDtoConverter.convert(
                        student = o,
                        personId = personId
                )
        )

        return o.login
    }

    override fun update(o: Student) {
        val studentModel = studentDao.findOne(o.login)

        if (studentModel != null) {
            personStorageService.updatePerson(
                    ExistingPersonInfo(
                            id = studentModel.personId,
                            person = o.person
                    )
            )

            studentDao.save(
                    StudentDtoConverter.convert(
                            student = o,
                            personId = studentModel.personId
                    )
            )
        }
    }

    override fun delete(id: String) {
        val studentModel = studentDao.findOne(id)

        if (studentModel != null) {
            studentDao.delete(id)
            personStorageService.deletePerson(studentModel.personId)
        }
    }
}
