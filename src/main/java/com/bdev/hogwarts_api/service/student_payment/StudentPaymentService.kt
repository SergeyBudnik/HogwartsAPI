package com.bdev.hogwarts_api.service.student_payment

import com.bdev.hogwarts_api.data.dto.student.ExistingStudentPayment
import com.bdev.hogwarts_api.data.dto.student.NewStudentPayment

interface StudentPaymentService {
    fun getPayments(): List<ExistingStudentPayment>
    fun getPayment(paymentId: Long): ExistingStudentPayment?
    fun exists(paymentId: Long): Boolean
    fun getPayments(studentLogin: String): List<ExistingStudentPayment>
    fun addPayment(payment: NewStudentPayment): Long
    fun updatePayment(payment: ExistingStudentPayment)
    fun deletePayment(paymentId: Long)
}
