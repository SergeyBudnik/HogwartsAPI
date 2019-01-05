package com.bdev.hogwarts_api.service.student_payment

import com.bdev.hogwarts_api.dao.StudentPaymentDao
import com.bdev.hogwarts_api.data.converter.student_payment.StudentPaymentDtoConverter
import com.bdev.hogwarts_api.data.converter.student_payment.StudentPaymentModelConverter
import com.bdev.hogwarts_api.data.dto.student.StudentPayment
import com.bdev.hogwarts_api.service.student.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.String.format

@Service
class StudentPaymentServiceImpl : StudentPaymentService {
    @Autowired
    private lateinit var studentPaymentDao: StudentPaymentDao

    @Autowired
    private lateinit var studentService: StudentService

    override fun getPayments(): List<StudentPayment> {
        return studentPaymentDao
                .findAll()
                .map { StudentPaymentModelConverter.convert(it) }
    }

    override fun exists(paymentId: Long): Boolean {
        return studentPaymentDao.exists(paymentId)
    }

    override fun getPayments(studentId: Long): List<StudentPayment> {
        if (studentService.getStudentById(studentId) == null) {
            throw RuntimeException(format("Student with id '%d' does not exist", studentId))
        }

        return studentPaymentDao
                .getAllByStudentId(studentId)
                .map { StudentPaymentModelConverter.convert(it) }
    }

    override fun addPayment(payment: StudentPayment): Long {
        if (studentService.getStudentById(payment.studentId) == null) {
            throw RuntimeException(format("Student with id '%d' does not exist", payment.studentId))
        }

        return studentPaymentDao
                .save(StudentPaymentDtoConverter.convert(payment)).id ?: throw RuntimeException()
    }

    override fun deletePayment(paymentId: Long) {
        if (!studentPaymentDao.exists(paymentId)) {
            throw RuntimeException(format("Student payment with id '%d' does not exist", paymentId))
        }

        studentPaymentDao.delete(paymentId)
    }
}
