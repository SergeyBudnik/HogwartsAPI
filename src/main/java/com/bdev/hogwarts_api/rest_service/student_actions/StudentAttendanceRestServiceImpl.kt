package com.bdev.hogwarts_api.rest_service.student_actions

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.student.StudentService
import com.bdev.hogwarts_api.service.student_attendance.StudentAttendanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class StudentAttendanceRestServiceImpl : StudentAttendanceRestService {
    @Autowired
    private lateinit var studentService: StudentService

    @Autowired
    private lateinit var studentAttendanceService: StudentAttendanceService

    @Transactional(readOnly = true)
    override fun getAllAttendances(userInfo: MunicipaliUserInfo): List<StudentAttendance> {
        return studentAttendanceService.getAllAttendances()
    }

    @Transactional(readOnly = true)
    override fun getAttendances(userInfo: MunicipaliUserInfo, studentId: Long): List<StudentAttendance> {
        if (studentService.getStudentById(studentId) == null) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentId)
        }

        return studentAttendanceService.getAttendances(studentId)
    }

    @Transactional
    override fun addAttendance(userInfo: MunicipaliUserInfo, attendance: StudentAttendance): Long {
        if (studentService.getStudentById(attendance.studentId) == null) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", attendance.studentId)
        }

        return studentAttendanceService.addAttendance(attendance)
    }

    @Transactional
    override fun deleteAttendance(userInfo: MunicipaliUserInfo, attendanceId: Long) {
        if (!studentAttendanceService.exists(attendanceId)) {
            throw HttpEntityNotFoundException("Student attendance with id '%d' does not exist", attendanceId)
        }

        studentAttendanceService.deleteAttendance(attendanceId)
    }
}
