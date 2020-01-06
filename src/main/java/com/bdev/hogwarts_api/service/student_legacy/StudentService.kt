package com.bdev.hogwarts_api.service.student_legacy

import com.bdev.hogwarts_api.data.dto.student.StudentLegacy

interface StudentService {
    fun getAllStudents(): List<StudentLegacy>
    fun exists(studentId: Long): Boolean
    fun getStudentById(studentId: Long): StudentLegacy?
    fun createStudent(student: StudentLegacy): Long
    fun editStudent(student: StudentLegacy)
    fun deleteStudent(studentId: Long)
}
