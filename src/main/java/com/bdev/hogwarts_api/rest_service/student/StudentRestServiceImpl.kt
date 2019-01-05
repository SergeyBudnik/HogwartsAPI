package com.bdev.hogwarts_api.rest_service.student

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.Student
import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.student.StudentService
import com.bdev.hogwarts_api.service.student_status.StudentStatusService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
open class StudentRestServiceImpl : StudentRestService {
    @Autowired
    private lateinit var studentService: StudentService
    @Autowired
    private lateinit var studentStatusService: StudentStatusService

    @Transactional(readOnly = true)
    override fun getAllStudents(userInfo: MunicipaliUserInfo): List<Student> {
        return studentService.getAllStudents()
    }

    @Transactional(readOnly = true)
    override fun getStudentById(userInfo: MunicipaliUserInfo, studentId: Long): Student {
        return studentService
                .getStudentById(studentId)
                ?: throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentId)
    }

    @Transactional
    override fun createStudent(userInfo: MunicipaliUserInfo, student: Student): Long {
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
            student: Student
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
