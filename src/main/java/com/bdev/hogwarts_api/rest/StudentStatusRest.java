package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.student.StudentStatus;
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType;
import com.bdev.hogwarts_api.rest_service.student_status.StudentStatusRestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students-status")
@Api(tags = "Student Status", description = "PROTECTED")
public class StudentStatusRest extends CommonRest {
    @Autowired
    private StudentStatusRestService studentStatusRestService;

    @GetMapping("/all")
    public List<StudentStatus> getStudentsStatuses(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    ) {
        return studentStatusRestService.getStudentsStatuses(
                getUserInfo(authToken)
        );
    }

    @GetMapping("/latest")
    public List<StudentStatus> getStudentsLatestStatuses(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    ) {
        return studentStatusRestService.getLatestStudentsStatuses(
                getUserInfo(authToken)
        );
    }

    @GetMapping("/{studentId}")
    public List<StudentStatus> getStudentStatuses(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long studentId
    ) {
        return studentStatusRestService.getStudentStatuses(
                getUserInfo(authToken),
                studentId
        );
    }

    @PutMapping("/{studentId}/{status}")
    public void changeStudentStatus(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long studentId,
            @PathVariable StudentStatusType status,
            @RequestBody long actionTime
    ) {
        studentStatusRestService.changeStudentStatus(
                getUserInfo(authToken),
                studentId,
                status,
                actionTime
        );
    }
}
