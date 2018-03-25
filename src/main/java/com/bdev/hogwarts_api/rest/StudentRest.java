package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.student.Student;
import com.bdev.hogwarts_api.rest_service.student.StudentRestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Api(tags = "Student", description = "PROTECTED")
public class StudentRest extends CommonRest {
    @Autowired
    private StudentRestService studentRestService;

    @GetMapping("")
    public List<Student> getAllStudents(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    ) {
        return studentRestService.getAllStudents(
                getUserInfo(authToken)
        );
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long studentId
    ) {
        return studentRestService.getStudentById(
                getUserInfo(authToken),
                studentId
        );
    }

    @PostMapping("")
    public long createStudent(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody Student student
    ) {
        return studentRestService.createStudent(
                getUserInfo(authToken),
                student
        );
    }

    @PutMapping("")
    public void editStudent(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody Student student
    ) {
        studentRestService.editStudent(
                getUserInfo(authToken),
                student
        );
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long studentId
    ) {
        studentRestService.deleteStudent(
                getUserInfo(authToken),
                studentId
        );
    }
}
