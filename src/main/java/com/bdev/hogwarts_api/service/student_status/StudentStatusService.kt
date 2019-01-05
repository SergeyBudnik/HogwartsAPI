package com.bdev.hogwarts_api.service.student_status

import com.bdev.hogwarts_api.data.dto.student.StudentStatus

interface StudentStatusService {
    fun getAllStudentsStatuses(): List<StudentStatus>
    fun getAllStudentStatuses(studentId: Long): List<StudentStatus>
    fun getStudentStatus(studentId: Long): StudentStatus?
    fun changeStudentStatus(studentStatus: StudentStatus)
}
