package com.bdev.hogwarts_api.rest_service.admin.student.student_status

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.student.StudentStorageService
import com.bdev.hogwarts_api.service.student_status.StudentStatusService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.Date

@Service
open class StudentStatusRestServiceImpl : StudentStatusRestService {
    @Autowired
    private lateinit var studentStorageService: StudentStorageService
    @Autowired
    private lateinit var studentStatusService: StudentStatusService

    override fun getStudentsStatuses(userInfo: MunicipaliUserInfo): List<StudentStatus> {
        return studentStatusService.getAllStudentsStatuses()
    }

    @Transactional(readOnly = true)
    override fun getLatestStudentsStatuses(userInfo: MunicipaliUserInfo): List<StudentStatus> {
        return studentStorageService
                .getAll()
                .map { it.login }
                .mapNotNull { studentStatusService.getStudentStatus(it) }
    }

    @Transactional(readOnly = true)
    override fun getStudentStatuses(userInfo: MunicipaliUserInfo, studentLogin: String): List<StudentStatus> {
        if (studentStorageService.getById(studentLogin) == null) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentLogin)
        }

        return studentStatusService.getAllStudentStatuses(studentLogin)
    }

    @Transactional
    override fun changeStudentStatus(userInfo: MunicipaliUserInfo, studentLogin: String, status: StudentStatusType, actionTime: Long) {
        if (studentStorageService.getById(studentLogin) == null) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentLogin)
        }

        studentStatusService.changeStudentStatus(StudentStatus(
                studentLogin = studentLogin,
                creationTime = Date().time,
                actionTime = actionTime,
                status = status
        ))
    }
}
