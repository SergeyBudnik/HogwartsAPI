package com.bdev.hogwarts_api.rest_service.student_legacy

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentLegacy

interface StudentRestServiceLegacy {
    fun getAllStudents(userInfo: MunicipaliUserInfo): List<StudentLegacy>
    fun getStudentById(userInfo: MunicipaliUserInfo, studentId: Long): StudentLegacy
    fun createStudent(userInfo: MunicipaliUserInfo, student: StudentLegacy): Long
    fun editStudent(userInfo: MunicipaliUserInfo, student: StudentLegacy)
    fun deleteStudent(userInfo: MunicipaliUserInfo, studentId: Long)
}
