package com.bdev.hogwarts_api.data.converter.student_attendance

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel

object StudentAttendanceDtoConverter {
    fun convert(studentAttendance: StudentAttendance): StudentAttendanceModel {
        val studentAttendanceModel = StudentAttendanceModel()

        studentAttendanceModel.id = studentAttendance.id
        studentAttendanceModel.studentId = studentAttendance.studentId
        studentAttendanceModel.type = studentAttendance.type
        studentAttendanceModel.groupType = studentAttendance.groupType
        studentAttendanceModel.studentsInGroup = studentAttendance.studentsInGroup
        studentAttendanceModel.startTime = studentAttendance.startTime
        studentAttendanceModel.finishTime = studentAttendance.finishTime

        return studentAttendanceModel
    }
}
