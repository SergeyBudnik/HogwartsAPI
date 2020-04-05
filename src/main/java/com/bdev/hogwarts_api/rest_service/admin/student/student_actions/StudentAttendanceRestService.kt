package com.bdev.hogwarts_api.rest_service.admin.student.student_actions

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceId

interface StudentAttendanceRestService {
    fun getAllAttendances(userInfo: MunicipaliUserInfo): List<StudentAttendance>
    fun getStudentAttendances(userInfo: MunicipaliUserInfo, studentLogin: String): List<StudentAttendance>
    fun addAttendance(userInfo: MunicipaliUserInfo, attendance: StudentAttendance)
    fun deleteAttendance(userInfo: MunicipaliUserInfo, attendanceId: StudentAttendanceId)
}
