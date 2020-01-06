package com.bdev.hogwarts_api.rest_service.student_status

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.student_legacy.StudentService
import com.bdev.hogwarts_api.service.student_status.StudentStatusService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.Date

@Service
open class StudentStatusRestServiceImpl : StudentStatusRestService {
    @Autowired
    private lateinit var studentService: StudentService
    @Autowired
    private lateinit var studentStatusService: StudentStatusService

    override fun getStudentsStatuses(userInfo: MunicipaliUserInfo): List<StudentStatus> {
        return studentStatusService.getAllStudentsStatuses()
    }

    @Transactional(readOnly = true)
    override fun getLatestStudentsStatuses(userInfo: MunicipaliUserInfo): List<StudentStatus> {
        return studentService
                .getAllStudents()
                .map { it.id ?: throw RuntimeException() }
                .mapNotNull { studentStatusService.getStudentStatus(it) }
    }

    @Transactional(readOnly = true)
    override fun getStudentStatuses(userInfo: MunicipaliUserInfo, studentId: Long): List<StudentStatus> {
        if (!studentService.exists(studentId)) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentId)
        }

        return studentStatusService.getAllStudentStatuses(studentId)
    }

    @Transactional
    override fun changeStudentStatus(userInfo: MunicipaliUserInfo, studentId: Long, status: StudentStatusType, actionTime: Long) {
        if (!studentService.exists(studentId)) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentId)
        }

        studentStatusService.changeStudentStatus(StudentStatus(
                studentId = studentId,
                creationTime = Date().time,
                actionTime = actionTime,
                status = status
        ))
    }
}
