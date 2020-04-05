package com.bdev.hogwarts_api.service.student_attendance

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceId

interface StudentAttendanceService {
    fun getAllAttendances(): List<StudentAttendance>
    fun getStudentAttendances(studentLogin: String): List<StudentAttendance>
    fun addAttendance(attendance: StudentAttendance)
    fun deleteAttendance(attendanceId: StudentAttendanceId)
}
