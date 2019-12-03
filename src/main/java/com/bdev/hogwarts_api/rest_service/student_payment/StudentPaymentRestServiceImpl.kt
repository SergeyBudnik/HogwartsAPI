package com.bdev.hogwarts_api.rest_service.student_payment

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentPayment
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.student.StudentService
import com.bdev.hogwarts_api.service.student_payment.StudentPaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class StudentPaymentRestServiceImpl : StudentPaymentRestService {
    @Autowired
    private lateinit var studentService: StudentService

    @Autowired
    private lateinit var studentPaymentService: StudentPaymentService

    @Transactional(readOnly = true)
    override fun getPayments(userInfo: MunicipaliUserInfo): List<StudentPayment> {
        return studentPaymentService.getPayments()
    }

    @Transactional(readOnly = true)
    override fun getPayments(userInfo: MunicipaliUserInfo, studentId: Long): List<StudentPayment> {
        if (!studentService.exists(studentId)) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentId)
        }

        return studentPaymentService.getPayments(studentId)
    }

    @Transactional
    override fun addPayment(userInfo: MunicipaliUserInfo, payment: StudentPayment): Long {
        if (!studentService.exists(payment.studentId)) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", payment.studentId)
        }

        return studentPaymentService.addPayment(payment)
    }

    @Transactional
    override fun setPaymentProcessed(userInfo: MunicipaliUserInfo, paymentId: Long, processed: Boolean) {
        val payment = studentPaymentService.getPayment(paymentId) ?:
                throw HttpEntityNotFoundException("Payment with id '%d' does not exist", paymentId)

        studentPaymentService.updatePayment(StudentPayment(
                id = payment.id,
                studentId = payment.studentId,
                staffMemberLogin = payment.staffMemberLogin,
                amount = payment.amount,
                time = payment.time,
                processed = processed
        ))
    }

    @Transactional
    override fun deletePayment(userInfo: MunicipaliUserInfo, paymentId: Long) {
        if (!studentPaymentService.exists(paymentId)) {
            throw HttpEntityNotFoundException("Student payment with id '%d' does not exist", paymentId)
        }

        studentPaymentService.deletePayment(paymentId)
    }
}
