package com.bdev.hogwarts_api.data.converter.student_payment

import com.bdev.hogwarts_api.data.dto.student.StudentPayment
import com.bdev.hogwarts_api.data.model.student_payment.StudentPaymentModel

object StudentPaymentDtoConverter {
    fun convert(studentPayment: StudentPayment): StudentPaymentModel {
        val studentPaymentModel = StudentPaymentModel()

        studentPaymentModel.id = studentPayment.id
        studentPaymentModel.studentId = studentPayment.studentId
        studentPaymentModel.teacherId = studentPayment.teacherId
        studentPaymentModel.amount = studentPayment.amount
        studentPaymentModel.time = studentPayment.time
        studentPaymentModel.processed = studentPayment.processed

        return studentPaymentModel
    }
}
