package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.student_payment.StudentPaymentModel
import org.springframework.data.jpa.repository.JpaRepository

interface StudentPaymentDao : JpaRepository<StudentPaymentModel, Long> {
    fun getAllByStudentLogin(studentLogin: String): List<StudentPaymentModel>
}
