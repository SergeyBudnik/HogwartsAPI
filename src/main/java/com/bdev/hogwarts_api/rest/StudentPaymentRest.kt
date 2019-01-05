package com.bdev.hogwarts_api.rest

import com.bdev.hogwarts_api.data.dto.student.StudentPayment
import com.bdev.hogwarts_api.rest_service.student_payment.StudentPaymentRestService
import io.swagger.annotations.Api
import lombok.Data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student-payment")
@Api(tags = ["Student Payment"], description = "PROTECTED")
class StudentPaymentRest : CommonRest() {
    @Autowired
    private lateinit var studentPaymentRestService: StudentPaymentRestService

    @GetMapping("")
    fun getPayments(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<StudentPayment> {
        return studentPaymentRestService.getPayments(
                getUserInfo(authToken)
        )
    }

    @GetMapping("/{studentId}")
    fun getPayments(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentId: Long
    ): List<StudentPayment> {
        return studentPaymentRestService.getPayments(
                getUserInfo(authToken),
                studentId
        )
    }

    @PostMapping("")
    fun addPayment(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody payment: StudentPayment
    ): Long {
        return studentPaymentRestService.addPayment(
                getUserInfo(authToken),
                StudentPayment(
                        studentId = payment.studentId,
                        amount = payment.amount,
                        time = payment.time
                )
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
