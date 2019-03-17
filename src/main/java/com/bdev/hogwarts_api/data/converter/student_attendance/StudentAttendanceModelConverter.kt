package com.bdev.hogwarts_api.data.converter.student_attendance

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel
import java.lang.RuntimeException

object StudentAttendanceModelConverter {
    fun convert(studentAttendanceModel: StudentAttendanceModel): StudentAttendance {
        return StudentAttendance(
                id = studentAttendanceModel.id,
                studentId = studentAttendanceModel.studentId ?: throw RuntimeException(),
                groupType = studentAttendanceModel.groupType ?: throw RuntimeException(),
                studentsInGroup = studentAttendanceModel.studentsInGroup ?: throw RuntimeException(),
                startTime = studentAttendanceModel.startTime ?: throw RuntimeException(),
                finishTime = studentAttendanceModel.finishTime ?: throw RuntimeException(),
                type = studentAttendanceModel.type ?: throw RuntimeException()
        )
    }
}
