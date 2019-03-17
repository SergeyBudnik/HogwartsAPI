package com.bdev.hogwarts_api.data.converter.student_payment

import com.bdev.hogwarts_api.data.dto.student.StudentPayment
import com.bdev.hogwarts_api.data.model.student_payment.StudentPaymentModel

object StudentPaymentModelConverter {
    fun convert(studentPaymentModel: StudentPaymentModel): StudentPayment {
        return StudentPayment(
                id = studentPaymentModel.id,
                studentId = studentPaymentModel.studentId ?: throw RuntimeException(),
                teacherId = studentPaymentModel.teacherId ?: throw RuntimeException(),
                amount = studentPaymentModel.amount ?: throw RuntimeException(),
                time = studentPaymentModel.time ?: throw RuntimeException()
        )
    }
}
