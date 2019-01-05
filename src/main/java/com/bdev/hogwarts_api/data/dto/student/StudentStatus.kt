package com.bdev.hogwarts_api.data.dto.student

class StudentStatus(
    var id: Long? = null,
    var studentId: Long = 0,
    var status: StudentStatusType = StudentStatusType.STUDYING,
    var creationTime: Long = 0,
    var actionTime: Long = 0
)