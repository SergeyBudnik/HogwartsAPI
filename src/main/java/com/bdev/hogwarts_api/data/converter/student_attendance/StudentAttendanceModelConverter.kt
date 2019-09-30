package com.bdev.hogwarts_api.data.converter.student_attendance

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel

object StudentAttendanceModelConverter {
    fun convert(studentAttendanceModel: StudentAttendanceModel): StudentAttendance {
        return StudentAttendance(
                studentId = studentAttendanceModel.id.studentId,
                groupType = studentAttendanceModel.groupType,
                studentsInGroup = studentAttendanceModel.studentsInGroup,
                startTime = studentAttendanceModel.id.startTime,
                finishTime = studentAttendanceModel.finishTime,
                type = studentAttendanceModel.type
        )
    }
}
