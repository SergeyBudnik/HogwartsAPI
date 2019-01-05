package com.bdev.hogwarts_api.rest_service.student_status

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType

interface StudentStatusRestService {
    fun getStudentsStatuses(userInfo: MunicipaliUserInfo): List<StudentStatus>
    fun getLatestStudentsStatuses(userInfo: MunicipaliUserInfo): List<StudentStatus>
    fun getStudentStatuses(userInfo: MunicipaliUserInfo, studentId: Long): List<StudentStatus>
    fun changeStudentStatus(userInfo: MunicipaliUserInfo, studentId: Long, status: StudentStatusType, actionTime: Long)
}
