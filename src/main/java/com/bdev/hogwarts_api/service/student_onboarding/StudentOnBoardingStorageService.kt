package com.bdev.hogwarts_api.service.student_onboarding

import com.bdev.hogwarts_api.dao.StudentOnBoardingDao
import com.bdev.hogwarts_api.data.converter.student_on_boarding.StudentOnBoardingDtoConverter
import com.bdev.hogwarts_api.data.converter.student_on_boarding.StudentOnBoardingModelConverter
import com.bdev.hogwarts_api.data.dto.person.ExistingPersonInfo
import com.bdev.hogwarts_api.data.dto.person.NewPersonInfo
import com.bdev.hogwarts_api.data.dto.student.on_boarding.ExistingStudentOnBoarding
import com.bdev.hogwarts_api.data.dto.student.on_boarding.NewStudentOnBoarding
import com.bdev.hogwarts_api.service.person.PersonStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface StudentOnBoardingStorageService {
    fun getAllStudentOnBoardings(): List<ExistingStudentOnBoarding>
    fun getStudentOnBoarding(login: String): ExistingStudentOnBoarding?
    fun existsStudentOnBoarding(login: String): Boolean
    fun createStudentOnBoarding(newStudentOnBoarding: NewStudentOnBoarding)
    fun updateStudentOnBoarding(existingStudentOnBoarding: ExistingStudentOnBoarding)
    fun deleteStudentOnBoarding(login: String)
}

@Service
open class StudentOnBoardingStorageServiceImpl @Autowired constructor(
        private val personStorageService: PersonStorageService,
        private val studentOnBoardingDao: StudentOnBoardingDao
) : StudentOnBoardingStorageService {
    override fun getAllStudentOnBoardings(): List<ExistingStudentOnBoarding> {
        return studentOnBoardingDao.findAll().mapNotNull {
            personStorageService.withExistingPerson(it.personId) { person ->
                StudentOnBoardingModelConverter.convert(
                        studentOnBoardingModel = it,
                        person = person
                )
            }
        }
    }

    override fun getStudentOnBoarding(login: String): ExistingStudentOnBoarding? {
        val studentOnBoardingModel = studentOnBoardingDao.findOne(login)

        return if (studentOnBoardingModel == null) {
            null
        } else {
            return personStorageService.withExistingPerson(studentOnBoardingModel.personId) { person ->
                StudentOnBoardingModelConverter.convert(
                        studentOnBoardingModel = studentOnBoardingModel,
                        person = person
                )
            }
        }
    }

    override fun existsStudentOnBoarding(login: String): Boolean {
        return studentOnBoardingDao.exists(login)
    }

    override fun createStudentOnBoarding(newStudentOnBoarding: NewStudentOnBoarding) {
        val personId = personStorageService.createPerson(
                NewPersonInfo(newStudentOnBoarding.info.person)
        )

        studentOnBoardingDao.save(
                StudentOnBoardingDtoConverter.convertNew(newStudentOnBoarding, personId)
        )
    }

    override fun updateStudentOnBoarding(existingStudentOnBoarding: ExistingStudentOnBoarding) {
        val studentOnBoardingModel = studentOnBoardingDao.findOne(existingStudentOnBoarding.info.login)

        if (studentOnBoardingModel != null) {
            personStorageService.updatePerson(
                    ExistingPersonInfo(
                            id = studentOnBoardingModel.personId,
                            person = existingStudentOnBoarding.info.person
                    )
            )

            studentOnBoardingDao.save(
                    StudentOnBoardingDtoConverter.convertExisting(
                            studentOnBoarding = existingStudentOnBoarding,
                            personId = studentOnBoardingModel.personId
                    )
            )
        }
    }

    override fun deleteStudentOnBoarding(login: String) {
        val studentOnBoardingModel = studentOnBoardingDao.findOne(login)

        if (studentOnBoardingModel != null) {
            studentOnBoardingDao.delete(login)

            personStorageService.deletePerson(studentOnBoardingModel.personId)
        }
    }
}