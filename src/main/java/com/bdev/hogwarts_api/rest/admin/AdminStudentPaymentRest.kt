package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.student.StudentPayment
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.student_payment.StudentPaymentRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student-payment")
@Api(tags = ["Student Payment"], description = "PROTECTED")
class AdminStudentPaymentRest : CommonRest() {
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
                        id = null,
                        studentId = payment.studentId,
                        amount = payment.amount,
                        time = payment.time,
                        teacherId = payment.teacherId,
                        processed = false
                )
        )
    }

    @PutMapping("/processed/{paymentId}")
    fun setPaymentProcessed(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable paymentId: Long
    ) {
        studentPaymentRestService.setPaymentProcessed(
                getUserInfo(authToken),
                paymentId
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
