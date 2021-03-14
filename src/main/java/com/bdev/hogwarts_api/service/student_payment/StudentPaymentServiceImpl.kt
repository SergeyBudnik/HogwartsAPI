package com.bdev.hogwarts_api.service.student_payment

import com.bdev.hogwarts_api.dao.StudentPaymentDao
import com.bdev.hogwarts_api.data.converter.student_payment.StudentPaymentDtoConverter
import com.bdev.hogwarts_api.data.converter.student_payment.StudentPaymentModelConverter
import com.bdev.hogwarts_api.data.dto.student.ExistingStudentPayment
import com.bdev.hogwarts_api.data.dto.student.NewStudentPayment
import com.bdev.hogwarts_api.service.student.StudentStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.String.format

@Service
class StudentPaymentServiceImpl : StudentPaymentService {
    @Autowired
    private lateinit var studentPaymentDao: StudentPaymentDao

    @Autowired
    private lateinit var studentStorageService: StudentStorageService

    override fun getPayments(): List<ExistingStudentPayment> {
        return studentPaymentDao
                .findAll()
                .map { StudentPaymentModelConverter.convert(it) }
    }

    override fun getPayment(paymentId: Long): ExistingStudentPayment? {
        if (!exists(paymentId)) {
            throw RuntimeException("Payment with id '$paymentId' does not exist")
        }

        return studentPaymentDao.findOne(paymentId)?.let { StudentPaymentModelConverter.convert(it) }
    }

    override fun exists(paymentId: Long): Boolean {
        return studentPaymentDao.exists(paymentId)
    }

    override fun getPayments(studentLogin: String): List<ExistingStudentPayment> {
        if (studentStorageService.getById(studentLogin) == null) {
            throw RuntimeException(format("Student with id '%d' does not exist", studentLogin))
        }

        return studentPaymentDao
                .getAllByStudentLogin(studentLogin)
                .map { StudentPaymentModelConverter.convert(it) }
    }

    override fun addPayment(payment: NewStudentPayment): Long {
        if (studentStorageService.getById(payment.info.studentLogin) == null) {
            throw RuntimeException(format("Student with id '%d' does not exist", payment.info.studentLogin))
        }

        return studentPaymentDao.save(
                StudentPaymentDtoConverter.convertNew(payment)
        ).id ?: throw RuntimeException()
    }

    override fun updatePayment(payment: ExistingStudentPayment) {
        if (payment.id == null) {
            throw RuntimeException("Payment id can't be null")
        }

        if (!exists(payment.id)) {
            throw RuntimeException("Payment with id '${payment.id}' does not exist")
        }

        studentPaymentDao.save(StudentPaymentDtoConverter.convertExisting(payment))
    }

    override fun deletePayment(paymentId: Long) {
        if (!studentPaymentDao.exists(paymentId)) {
            throw RuntimeException(format("Student payment with id '%d' does not exist", paymentId))
        }

        studentPaymentDao.delete(paymentId)
    }
}
