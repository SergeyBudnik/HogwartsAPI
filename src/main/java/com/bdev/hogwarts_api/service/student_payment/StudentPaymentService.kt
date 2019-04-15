package com.bdev.hogwarts_api.service.student_payment

import com.bdev.hogwarts_api.data.dto.student.StudentPayment

interface StudentPaymentService {
    fun getPayments(): List<StudentPayment>
    fun getPayment(paymentId: Long): StudentPayment?
    fun exists(paymentId: Long): Boolean
    fun getPayments(studentId: Long): List<StudentPayment>
    fun addPayment(payment: StudentPayment): Long
    fun updatePayment(payment: StudentPayment)
    fun deletePayment(paymentId: Long)
}
