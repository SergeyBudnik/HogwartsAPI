package com.bdev.hogwarts_api.service.student_attendance

import com.bdev.hogwarts_api.dao.StudentAttendanceDao
import com.bdev.hogwarts_api.data.converter.student_attendance.StudentAttendanceDtoConverter
import com.bdev.hogwarts_api.data.converter.student_attendance.StudentAttendanceModelConverter
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModelId
import com.bdev.hogwarts_api.service.student_legacy.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.String.format

@Service
class StudentAttendanceServiceImpl : StudentAttendanceService {
    @Autowired
    private lateinit var studentAttendanceDao: StudentAttendanceDao

    @Autowired
    private lateinit var studentService: StudentService

    override fun getAllAttendances(): List<StudentAttendance> {
        return studentAttendanceDao
                .findAll()
                .map { StudentAttendanceModelConverter.convert(it) }
    }

    override fun getAttendances(studentId: Long): List<StudentAttendance> {
        if (!studentService.exists(studentId)) {
            throw RuntimeException(format("Student with id '%d' does not exist", studentId))
        }

        return studentAttendanceDao
                .getAllByIdStudentId(studentId)
                .map { StudentAttendanceModelConverter.convert(it) }
    }

    override fun addAttendance(attendance: StudentAttendance) {
        if (!studentService.exists(attendance.studentId)) {
            throw RuntimeException(format("Student with id '%d' does not exist", attendance.studentId))
        }

        studentAttendanceDao.save(StudentAttendanceDtoConverter.convert(attendance))
    }

    override fun deleteAttendance(studentId: Long, startTime: Long) {
        studentAttendanceDao.delete(
                StudentAttendanceModelId(
                        studentId = studentId,
                        startTime = startTime
                )
        )
    }
}
