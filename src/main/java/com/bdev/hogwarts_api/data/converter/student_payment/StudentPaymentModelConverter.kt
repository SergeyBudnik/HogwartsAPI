package com.bdev.hogwarts_api.data.converter.student_payment

import com.bdev.hogwarts_api.data.dto.student.ExistingStudentPayment
import com.bdev.hogwarts_api.data.dto.student.StudentPaymentInfo
import com.bdev.hogwarts_api.data.model.student_payment.StudentPaymentModel

object StudentPaymentModelConverter {
    fun convert(studentPaymentModel: StudentPaymentModel): ExistingStudentPayment {
        return ExistingStudentPayment(
                id = studentPaymentModel.id,
                info = StudentPaymentInfo(
                    studentLogin = studentPaymentModel.studentLogin ?: throw RuntimeException(),
                    staffMemberLogin = studentPaymentModel.staffMemberLogin ?: throw RuntimeException(),
                    amount = studentPaymentModel.amount ?: throw RuntimeException(),
                    time = studentPaymentModel.time ?: throw RuntimeException()
                ),
                processed = studentPaymentModel.processed ?: throw RuntimeException()
        )
    }
}
