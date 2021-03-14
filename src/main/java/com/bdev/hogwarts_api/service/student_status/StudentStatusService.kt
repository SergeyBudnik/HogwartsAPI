package com.bdev.hogwarts_api.service.student_status

import com.bdev.hogwarts_api.data.dto.student.StudentStatus

interface StudentStatusService {
    fun getAllStudentsStatuses(): List<StudentStatus>
    fun getAllStudentStatuses(studentLogin: String): List<StudentStatus>
    fun getStudentStatus(studentLogin: String): StudentStatus?
    fun changeStudentStatus(studentStatus: StudentStatus)
}
