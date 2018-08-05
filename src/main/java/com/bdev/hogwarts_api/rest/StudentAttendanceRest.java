package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance;
import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceType;
import com.bdev.hogwarts_api.rest_service.student_actions.StudentAttendanceRestService;
import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-attendance")
@Api(tags = "Student Attendance", description = "PROTECTED")
public class StudentAttendanceRest extends CommonRest {
    @Autowired
    private StudentAttendanceRestService studentAttendanceRestService;

    @GetMapping("")
    public List<StudentAttendance> getAllAttendances(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    ) {
        return studentAttendanceRestService.getAllAttendances(
                getUserInfo(authToken)
        );
    }

    @GetMapping("/{studentId}")
    public List<StudentAttendance> getAttendances(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long studentId
    ) {
        return studentAttendanceRestService.getAttendances(
                getUserInfo(authToken),
                studentId
        );
    }

    @PostMapping()
    public long addAttendance(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody StudentAttendance attendance
    ) {
        return studentAttendanceRestService.addAttendance(
                getUserInfo(authToken),
                StudentAttendance
                        .builder()
                        .studentId(attendance.getStudentId())
                        .type(attendance.getType())
                        .time(attendance.getTime())
                        .build()
        );
    }

    @DeleteMapping("/{attendanceId}")
    private void deleteAttendance(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long attendanceId
    ) {
        studentAttendanceRestService.deleteAttendance(
                getUserInfo(authToken),
                attendanceId
        );
    }
}
