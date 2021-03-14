package com.bdev.hogwarts_api.rest.admin.student

import com.bdev.hogwarts_api.data.dto.student.ExistingStudentPayment
import com.bdev.hogwarts_api.data.dto.student.NewStudentPayment
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.student.student_payment.StudentPaymentRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/students/payments")
@Api(tags = ["Admin Students Payments"], description = "PROTECTED")
class AdminStudentPaymentRest @Autowired constructor(
        private val studentPaymentRestService: StudentPaymentRestService
): CommonRest() {
    @GetMapping("")
    fun getPayments(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<ExistingStudentPayment> {
        return studentPaymentRestService.getPayments(
                userInfo = getUserInfo(authToken)
        )
    }

    @GetMapping("/student/{studentLogin}")
    fun getPayments(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentLogin: String
    ): List<ExistingStudentPayment> {
        return studentPaymentRestService.getStudentPayments(
                userInfo = getUserInfo(authToken),
                studentLogin = studentLogin
        )
    }

    @PostMapping("")
    fun addPayment(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody payment: NewStudentPayment
    ): Long {
        return studentPaymentRestService.addPayment(
                userInfo = getUserInfo(authToken),
                payment = payment
        )
    }

    @PutMapping("/{paymentId}/processed/{processed}")
    fun setPaymentProcessed(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable paymentId: Long,
            @PathVariable processed: Boolean
    ) {
        studentPaymentRestService.setPaymentProcessed(
                userInfo = getUserInfo(authToken),
                paymentId = paymentId,
                processed = processed
        )
    }

    @DeleteMapping("/{paymentId}")
    fun deletePayment(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable paymentId: Long
    ) {
        studentPaymentRestService.deletePayment(
                getUserInfo(authToken),
                paymentId
        )
    }
}
