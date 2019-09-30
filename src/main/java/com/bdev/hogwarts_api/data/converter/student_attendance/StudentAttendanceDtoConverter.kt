package com.bdev.hogwarts_api.data.converter.student_attendance

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModelId

object StudentAttendanceDtoConverter {
    fun convert(studentAttendance: StudentAttendance): StudentAttendanceModel {
        return StudentAttendanceModel(
                id = StudentAttendanceModelId(
                        studentId = studentAttendance.studentId,
                        startTime = studentAttendance.startTime
                ),
                type = studentAttendance.type,
                groupType = studentAttendance.groupType,
                studentsInGroup = studentAttendance.studentsInGroup,
                finishTime = studentAttendance.finishTime
        )
    }
}
