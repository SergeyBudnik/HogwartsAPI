package com.bdev.hogwarts_api.rest_service.student_legacy

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentLegacy
import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.student_legacy.StudentService
import com.bdev.hogwarts_api.service.student_status.StudentStatusService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
open class StudentRestServiceImpl : StudentRestServiceLegacy {
    @Autowired
    private lateinit var studentService: StudentService
    @Autowired
    private lateinit var studentStatusService: StudentStatusService

    @Transactional(readOnly = true)
    override fun getAllStudents(userInfo: MunicipaliUserInfo): List<StudentLegacy> {
        return studentService.getAllStudents()
    }

    @Transactional(readOnly = true)
    override fun getStudentById(userInfo: MunicipaliUserInfo, studentId: Long): StudentLegacy {
        return studentService
                .getStudentById(studentId)
                ?: throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentId)
    }

    @Transactional
    override fun createStudent(userInfo: MunicipaliUserInfo, student: StudentLegacy): Long {
        if (student.id != null) {
            throw HttpEntityIllegalStateException("Student id should be null during creation")
        }

        val studentId = studentService.createStudent(student)

        studentStatusService.changeStudentStatus(StudentStatus(
                studentId = studentId,
                creationTime = Date().time,
                actionTime = -1L,
                status = student.statusType
        ))

        return studentId
    }

    @Transactional
    override fun editStudent(
            userInfo: MunicipaliUserInfo,
            student: StudentLegacy
    ) {
        val studentId = student.id ?:
            throw HttpEntityIllegalStateException("Student id should be non-null during edit")

        if (!studentService.exists(studentId)) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentId)
        }

        studentService.editStudent(student)
    }

    @Transactional
    override fun deleteStudent(
            userInfo: MunicipaliUserInfo,
            studentId: Long
    ) {
        if (!studentService.exists(studentId)) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentId)
        }

        studentService.deleteStudent(studentId)
    }
}
