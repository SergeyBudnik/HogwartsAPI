package com.bdev.hogwarts_api.data.dto.student

data class StudentStatus(
    var id: Long? = null,
    var studentLogin: String = "",
    var status: StudentStatusType = StudentStatusType.STUDYING,
    var creationTime: Long = 0,
    var actionTime: Long = 0
)