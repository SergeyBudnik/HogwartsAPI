package com.bdev.hogwarts_api.rest_service.student_payment

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.StudentPayment

interface StudentPaymentRestService {
    fun getPayments(userInfo: MunicipaliUserInfo): List<StudentPayment>
    fun getPayments(userInfo: MunicipaliUserInfo, studentId: Long): List<StudentPayment>
    fun addPayment(userInfo: MunicipaliUserInfo, payment: StudentPayment): Long
    fun setPaymentProcessed(userInfo: MunicipaliUserInfo, paymentId: Long, processed: Boolean)
    fun deletePayment(userInfo: MunicipaliUserInfo, paymentId: Long)
}
