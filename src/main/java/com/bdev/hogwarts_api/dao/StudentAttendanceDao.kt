package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModelId
import org.springframework.data.jpa.repository.JpaRepository

interface StudentAttendanceDao : JpaRepository<StudentAttendanceModel, StudentAttendanceModelId> {
    fun getAllByIdStudentLogin(studentLogin: String): List<StudentAttendanceModel>
}