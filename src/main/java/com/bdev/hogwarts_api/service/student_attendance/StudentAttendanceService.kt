package com.bdev.hogwarts_api.service.student_attendance

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance

interface StudentAttendanceService {
    fun getAllAttendances(): List<StudentAttendance>
    fun getAttendances(studentId: Long): List<StudentAttendance>
    fun addAttendance(attendance: StudentAttendance)
    fun deleteAttendance(studentId: Long, startTime: Long)
}
