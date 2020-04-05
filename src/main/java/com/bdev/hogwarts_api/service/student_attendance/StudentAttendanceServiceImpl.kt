package com.bdev.hogwarts_api.service.student_attendance

import com.bdev.hogwarts_api.dao.StudentAttendanceDao
import com.bdev.hogwarts_api.data.converter.student_attendance.StudentAttendanceDtoConverter
import com.bdev.hogwarts_api.data.converter.student_attendance.StudentAttendanceModelConverter
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceId
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModelId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentAttendanceServiceImpl @Autowired constructor(
        private val studentAttendanceDao: StudentAttendanceDao
): StudentAttendanceService {
    override fun getAllAttendances(): List<StudentAttendance> {
        return studentAttendanceDao
                .findAll()
                .map { StudentAttendanceModelConverter.convert(it) }
    }

    override fun getStudentAttendances(studentLogin: String): List<StudentAttendance> {
        return studentAttendanceDao
                .getAllByIdStudentLogin(studentLogin)
                .map { StudentAttendanceModelConverter.convert(it) }
    }

    override fun addAttendance(attendance: StudentAttendance) {
        studentAttendanceDao.save(StudentAttendanceDtoConverter.convert(attendance))
    }

    override fun deleteAttendance(attendanceId: StudentAttendanceId) {
        studentAttendanceDao.delete(
                StudentAttendanceModelId(
                        studentLogin = attendanceId.studentLogin,
                        startTime = attendanceId.startTime,
                        finishTime = attendanceId.finishTime
                )
        )
    }
}
