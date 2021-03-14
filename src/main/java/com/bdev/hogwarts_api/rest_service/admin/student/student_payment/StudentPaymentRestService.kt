package com.bdev.hogwarts_api.rest_service.admin.student.student_payment

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.ExistingStudentPayment
import com.bdev.hogwarts_api.data.dto.student.NewStudentPayment

interface StudentPaymentRestService {
    fun getPayments(userInfo: MunicipaliUserInfo): List<ExistingStudentPayment>
    fun getStudentPayments(userInfo: MunicipaliUserInfo, studentLogin: String): List<ExistingStudentPayment>
    fun addPayment(userInfo: MunicipaliUserInfo, payment: NewStudentPayment): Long
    fun deletePayment(userInfo: MunicipaliUserInfo, paymentId: Long)
    fun setPaymentProcessed(userInfo: MunicipaliUserInfo, paymentId: Long, processed: Boolean)
}
