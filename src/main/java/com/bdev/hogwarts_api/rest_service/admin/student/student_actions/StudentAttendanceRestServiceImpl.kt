package com.bdev.hogwarts_api.rest_service.admin.student.student_actions

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceId
import com.bdev.hogwarts_api.service.student_attendance.StudentAttendanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class StudentAttendanceRestServiceImpl @Autowired constructor(
        private val studentAttendanceService: StudentAttendanceService
): StudentAttendanceRestService {
    @Transactional(readOnly = true)
    override fun getAllAttendances(userInfo: MunicipaliUserInfo): List<StudentAttendance> {
        return studentAttendanceService.getAllAttendances()
    }

    @Transactional(readOnly = true)
    override fun getStudentAttendances(userInfo: MunicipaliUserInfo, studentLogin: String): List<StudentAttendance> {
        return studentAttendanceService.getStudentAttendances(studentLogin = studentLogin)
    }

    @Transactional
    override fun addAttendance(userInfo: MunicipaliUserInfo, attendance: StudentAttendance) {
        studentAttendanceService.addAttendance(attendance = attendance)
    }

    override fun deleteAttendance(userInfo: MunicipaliUserInfo, attendanceId: StudentAttendanceId) {
        studentAttendanceService.deleteAttendance(attendanceId = attendanceId)
    }
}
