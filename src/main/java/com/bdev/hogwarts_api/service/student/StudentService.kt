package com.bdev.hogwarts_api.service.student

import com.bdev.hogwarts_api.data.dto.student.Student

interface StudentService {
    fun getAllStudents(): List<Student>
    fun exists(studentId: Long): Boolean
    fun getStudentById(studentId: Long): Student?
    fun createStudent(student: Student): Long
    fun editStudent(student: Student)
    fun deleteStudent(studentId: Long)
}
