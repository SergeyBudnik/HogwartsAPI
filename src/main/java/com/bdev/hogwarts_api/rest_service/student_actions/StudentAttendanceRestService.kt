package com.bdev.hogwarts_api.rest_service.student_actions

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.dto.student.StudentPayment

interface StudentAttendanceRestService {
    fun getAllAttendances(userInfo: MunicipaliUserInfo): List<StudentAttendance>
    fun getAttendances(userInfo: MunicipaliUserInfo, studentId: Long): List<StudentAttendance>
    fun addAttendance(userInfo: MunicipaliUserInfo, attendance: StudentAttendance): Long
    fun deleteAttendance(userInfo: MunicipaliUserInfo, attendanceId: Long)
}
