package com.bdev.hogwarts_api.rest_service.student

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.Student
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType

interface StudentRestService {
    fun getAllStudents(userInfo: MunicipaliUserInfo): List<Student>
    fun getStudentById(userInfo: MunicipaliUserInfo, studentId: Long): Student
    fun createStudent(userInfo: MunicipaliUserInfo, student: Student): Long
    fun editStudent(userInfo: MunicipaliUserInfo, student: Student)
    fun deleteStudent(userInfo: MunicipaliUserInfo, studentId: Long)
}
