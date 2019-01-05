package com.bdev.hogwarts_api.data.dto.student

class StudentPayment(
    var id: Long? = null,
    var studentId: Long = 0,
    var amount: Long = 0,
    var time: Long = 0
)
