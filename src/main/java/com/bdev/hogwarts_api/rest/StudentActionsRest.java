package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance;
import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceType;
import com.bdev.hogwarts_api.data.dto.student.StudentPayment;
import com.bdev.hogwarts_api.rest_service.payment.StudentActionsRestService;
import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student-actions")
@Api(tags = "Student Actions", description = "PROTECTED")
public class StudentActionsRest extends CommonRest {
    @Data
    private static class Payment {
        private long amount;
        private long time;
    }

    @Data
    private static class Attendance {
        private StudentAttendanceType type;
        private long time;
    }

    @Autowired
    private StudentActionsRestService studentActionsRestService;

    @PostMapping("/{studentId}/payment")
    public void addPayment(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long studentId,
            @RequestBody Payment payment
    ) {
        studentActionsRestService.addPayment(
                getUserInfo(authToken),
                StudentPayment
                        .builder()
                        .studentId(studentId)
                        .amount(payment.getAmount())
                        .time(payment.getTime())
                        .build()
        );
    }

    @PostMapping("/{studentId}/attendance")
    public void addAttendance(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long studentId,
            @RequestBody Attendance attendance
    ) {
        studentActionsRestService.addAttendance(
                getUserInfo(authToken),
                StudentAttendance
                        .builder()
                        .studentId(studentId)
                        .type(attendance.getType())
                        .time(attendance.getTime())
                        .build()
        );
    }
}
