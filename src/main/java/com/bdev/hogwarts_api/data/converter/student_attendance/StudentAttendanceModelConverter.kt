package com.bdev.hogwarts_api.data.converter.student_attendance

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModelId

object StudentAttendanceModelConverter {
    fun convert(studentAttendanceModel: StudentAttendanceModel): StudentAttendance {
        return StudentAttendance(
                studentLogin = studentAttendanceModel.id.studentLogin,
                startTime = studentAttendanceModel.id.startTime,
                finishTime = studentAttendanceModel.id.finishTime,
                type = studentAttendanceModel.type,
                groupType = studentAttendanceModel.groupType,
                studentsInGroup = studentAttendanceModel.studentsInGroup
        )
    }
}