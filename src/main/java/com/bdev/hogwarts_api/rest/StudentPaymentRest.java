package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.student.StudentPayment;
import com.bdev.hogwarts_api.rest_service.student_payment.StudentPaymentRestService;
import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-payment")
@Api(tags = "Student Payment", description = "PROTECTED")
public class StudentPaymentRest extends CommonRest {
    @Autowired
    private StudentPaymentRestService studentPaymentRestService;

    @GetMapping("")
    public List<StudentPayment> getPayments(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    ) {
        return studentPaymentRestService.getPayments(
                getUserInfo(authToken)
        );
    }

    @GetMapping("/{studentId}")
    public List<StudentPayment> getPayments(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long studentId
    ) {
        return studentPaymentRestService.getPayments(
                getUserInfo(authToken),
                studentId
        );
    }

    @PostMapping("")
    public long addPayment(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody StudentPayment payment
    ) {
        return studentPaymentRestService.addPayment(
                getUserInfo(authToken),
                StudentPayment
                        .builder()
                        .studentId(payment.getStudentId())
                        .amount(payment.getAmount())
                        .time(payment.getTime())
                        .build()
        );
    }

    @DeleteMapping("/{paymentId}")
    private void deletePayment(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long paymentId
    ) {
        studentPaymentRestService.deletePayment(
                getUserInfo(authToken),
                paymentId
        );
    }
}
