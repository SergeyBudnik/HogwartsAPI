package com.bdev.hogwarts_api.rest_service.admin.student.student_payment

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.ExistingStudentPayment
import com.bdev.hogwarts_api.data.dto.student.NewStudentPayment
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.student.StudentStorageService
import com.bdev.hogwarts_api.service.student_payment.StudentPaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class StudentPaymentRestServiceImpl : StudentPaymentRestService {
    @Autowired
    private lateinit var studentStorageService: StudentStorageService

    @Autowired
    private lateinit var studentPaymentService: StudentPaymentService

    @Transactional(readOnly = true)
    override fun getPayments(userInfo: MunicipaliUserInfo): List<ExistingStudentPayment> {
        return studentPaymentService.getPayments()
    }

    @Transactional(readOnly = true)
    override fun getStudentPayments(userInfo: MunicipaliUserInfo, studentLogin: String): List<ExistingStudentPayment> {
        if (studentStorageService.getById(studentLogin) == null) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", studentLogin)
        }

        return studentPaymentService.getPayments(studentLogin)
    }

    @Transactional
    override fun addPayment(userInfo: MunicipaliUserInfo, payment: NewStudentPayment): Long {
        if (studentStorageService.getById(payment.info.studentLogin) == null) {
            throw HttpEntityNotFoundException("Student with id '%d' does not exist", payment.info.studentLogin)
        }

        return studentPaymentService.addPayment(payment)
    }

    @Transactional
    override fun setPaymentProcessed(userInfo: MunicipaliUserInfo, paymentId: Long, processed: Boolean) {
        val payment = studentPaymentService.getPayment(paymentId) ?:
                throw HttpEntityNotFoundException("Payment with id '%d' does not exist", paymentId)

        studentPaymentService.updatePayment(
                payment.copy(processed = processed)
        )
    }

    @Transactional
    override fun deletePayment(userInfo: MunicipaliUserInfo, paymentId: Long) {
        if (!studentPaymentService.exists(paymentId)) {
            throw HttpEntityNotFoundException("Student payment with id '%d' does not exist", paymentId)
        }

        studentPaymentService.deletePayment(paymentId)
    }
}
