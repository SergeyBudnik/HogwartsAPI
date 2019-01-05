package com.bdev.hogwarts_api.data.converter.student_attendance

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel
import java.lang.RuntimeException

object StudentAttendanceModelConverter {
    fun convert(studentAttendanceModel: StudentAttendanceModel): StudentAttendance {
        return StudentAttendance(
                id = studentAttendanceModel.id,
                studentId = studentAttendanceModel.studentId ?: throw RuntimeException(),
                time = studentAttendanceModel.time ?: throw RuntimeException(),
                type = studentAttendanceModel.type ?: throw RuntimeException()
        )
    }
}
