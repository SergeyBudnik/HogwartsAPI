package com.bdev.hogwarts_api.rest_service.admin.student_onboarding

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.on_boarding.*
import com.bdev.hogwarts_api.service.student_onboarding.StudentOnBoardingNotificationsService
import com.bdev.hogwarts_api.service.student_onboarding.StudentOnBoardingStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface AdminStudentOnBoardingRestService {
    fun getAllStudentOnBoardings(
            userInfo: MunicipaliUserInfo
    ): ResponseEntity<Any>

    fun getStudentOnBoarding(
            userInfo: MunicipaliUserInfo,
            login: String
    ): ResponseEntity<Any>

    fun createStudentOnBoarding(
            userInfo: MunicipaliUserInfo,
            newStudentOnBoarding: NewStudentOnBoarding
    ): ResponseEntity<Any>

    fun updateStudentOnBoarding(
            userInfo: MunicipaliUserInfo,
            login: String,
            studentOnBoardingInfo: StudentOnBoardingInfo
    ): ResponseEntity<Any>

    fun updateStudentOnBoardingResult(
            userInfo: MunicipaliUserInfo,
            login: String,
            studentOnBoardingResult: StudentOnBoardingResult
    ): ResponseEntity<Any>

    fun completeStudentOnBoardingAction(
            userInfo: MunicipaliUserInfo,
            login: String,
            studentOnBoardingAction: NewStudentOnBoardingAction
    ): ResponseEntity<Any>

    fun deleteStudentOnBoarding(
            userInfo: MunicipaliUserInfo,
            login: String
    ): ResponseEntity<Any>
}

@Service
open class AdminStudentOnBoardingRestServiceImpl @Autowired constructor(
        private val studentOnBoardingStorageService: StudentOnBoardingStorageService,
        private val studentOnBoardingNotificationsService: StudentOnBoardingNotificationsService
) : AdminStudentOnBoardingRestService {
    @Transactional(readOnly = true)
    override fun getAllStudentOnBoardings(
            userInfo: MunicipaliUserInfo
    ): ResponseEntity<Any> {
        val allStudentOnBoardings = studentOnBoardingStorageService.getAllStudentOnBoardings()

        return ResponseEntity.ok(allStudentOnBoardings)
    }

    @Transactional(readOnly = true)
    override fun getStudentOnBoarding(
            userInfo: MunicipaliUserInfo,
            login: String
    ): ResponseEntity<Any> {
        val studentOnBoarding = studentOnBoardingStorageService.getStudentOnBoarding(login = login)

        return if (studentOnBoarding != null) {
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body(studentOnBoarding)
        } else {
            ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Entity with login '$login' does not exist")
        }
    }

    @Transactional
    override fun createStudentOnBoarding(
            userInfo: MunicipaliUserInfo,
            newStudentOnBoarding: NewStudentOnBoarding
    ): ResponseEntity<Any> {
        val studentOnBoardingExists = studentOnBoardingStorageService.existsStudentOnBoarding(login = newStudentOnBoarding.info.login)

        return if (studentOnBoardingExists) {
            ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Entity with login '${newStudentOnBoarding.info.login}' already exists")
        } else {
            studentOnBoardingStorageService.createStudentOnBoarding(newStudentOnBoarding = newStudentOnBoarding)

            studentOnBoardingNotificationsService.notifyAction(
                    info = newStudentOnBoarding.info,
                    action = newStudentOnBoarding.action
            )

            ResponseEntity
                    .status(HttpStatus.OK)
                    .build()
        }
    }

    @Transactional
    override fun updateStudentOnBoarding(
            userInfo: MunicipaliUserInfo,
            login: String,
            studentOnBoardingInfo: StudentOnBoardingInfo
    ): ResponseEntity<Any> {
        return withStudentOnBoarding(login = login) {
            studentOnBoardingStorageService.updateStudentOnBoarding(
                    existingStudentOnBoarding = it.copy(info = studentOnBoardingInfo)
            )
        }
    }

    @Transactional
    override fun updateStudentOnBoardingResult(
            userInfo: MunicipaliUserInfo,
            login: String,
            studentOnBoardingResult: StudentOnBoardingResult
    ): ResponseEntity<Any> {
        return withStudentOnBoarding(login = login) {
            studentOnBoardingStorageService.updateStudentOnBoarding(
                    existingStudentOnBoarding = it.copy(result = studentOnBoardingResult)
            )
        }
    }

    @Transactional
    override fun completeStudentOnBoardingAction(
            userInfo: MunicipaliUserInfo,
            login: String,
            studentOnBoardingAction: NewStudentOnBoardingAction
    ): ResponseEntity<Any> {
        return withStudentOnBoarding(login = login) { oldStudentOnBoarding ->
            val newActions = oldStudentOnBoarding
                    .actions
                    .map { action ->
                        if (action.completionTime == null) {
                            action.copy(completionTime = Date().time)
                        } else {
                            action
                        }
                    }
                    .plus(ExistingStudentOnBoardingAction(
                            info = studentOnBoardingAction.info,
                            creationTime = Date().time,
                            completionTime = null
                    ))

            studentOnBoardingNotificationsService.notifyAction(
                    info = oldStudentOnBoarding.info,
                    action = studentOnBoardingAction
            )

            studentOnBoardingStorageService.updateStudentOnBoarding(
                    existingStudentOnBoarding = oldStudentOnBoarding.copy(actions = newActions)
            )
        }
    }

    @Transactional
    override fun deleteStudentOnBoarding(
            userInfo: MunicipaliUserInfo,
            login: String
    ): ResponseEntity<Any> {
        return withStudentOnBoarding(login = login) {
            studentOnBoardingStorageService.deleteStudentOnBoarding(login)
        }
    }

    private fun withStudentOnBoarding(login: String, action: (ExistingStudentOnBoarding) -> Unit): ResponseEntity<Any> {
        val studentOnBoarding = studentOnBoardingStorageService.getStudentOnBoarding(login = login)

        return if (studentOnBoarding != null) {
            action.invoke(studentOnBoarding)

            ResponseEntity.status(HttpStatus.OK).build()
        } else {
            ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Entity with login '$login' does not exist")
        }
    }
}