package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel
import org.springframework.data.jpa.repository.JpaRepository

interface StudentAttendanceDao : JpaRepository<StudentAttendanceModel, Long> {
    fun getAllByStudentId(studentId: Long): List<StudentAttendanceModel>
}
