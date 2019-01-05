package com.bdev.hogwarts_api.data.dto.student

class StudentAttendance(
    var id: Long? = null,
    var studentId: Long = 0,
    var type: StudentAttendanceType = StudentAttendanceType.VISITED,
    var time: Long = 0
)
