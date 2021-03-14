package com.bdev.hogwarts_api.rest_service.admin.student.student_status

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType

interface StudentStatusRestService {
    fun getStudentsStatuses(userInfo: MunicipaliUserInfo): List<StudentStatus>
    fun getLatestStudentsStatuses(userInfo: MunicipaliUserInfo): List<StudentStatus>
    fun getStudentStatuses(userInfo: MunicipaliUserInfo, studentLogin: String): List<StudentStatus>
    fun changeStudentStatus(userInfo: MunicipaliUserInfo, studentLogin: String, status: StudentStatusType, actionTime: Long)
}
