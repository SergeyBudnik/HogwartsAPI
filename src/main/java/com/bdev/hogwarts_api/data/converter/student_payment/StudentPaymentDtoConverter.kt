package com.bdev.hogwarts_api.data.converter.student_payment

import com.bdev.hogwarts_api.data.dto.student.ExistingStudentPayment
import com.bdev.hogwarts_api.data.dto.student.NewStudentPayment
import com.bdev.hogwarts_api.data.model.student_payment.StudentPaymentModel

object StudentPaymentDtoConverter {
    fun convertNew(studentPayment: NewStudentPayment): StudentPaymentModel {
        val studentPaymentModel = StudentPaymentModel()

        studentPaymentModel.id = null

        studentPaymentModel.studentLogin = studentPayment.info.studentLogin
        studentPaymentModel.staffMemberLogin = studentPayment.info.staffMemberLogin
        studentPaymentModel.amount = studentPayment.info.amount
        studentPaymentModel.time = studentPayment.info.time

        studentPaymentModel.processed = false

        return studentPaymentModel
    }

    fun convertExisting(studentPayment: ExistingStudentPayment): StudentPaymentModel {
        val studentPaymentModel = StudentPaymentModel()

        studentPaymentModel.id = studentPayment.id

        studentPaymentModel.studentLogin = studentPayment.info.studentLogin
        studentPaymentModel.staffMemberLogin = studentPayment.info.staffMemberLogin
        studentPaymentModel.amount = studentPayment.info.amount
        studentPaymentModel.time = studentPayment.info.time

        studentPaymentModel.processed = studentPayment.processed

        return studentPaymentModel
    }
}
