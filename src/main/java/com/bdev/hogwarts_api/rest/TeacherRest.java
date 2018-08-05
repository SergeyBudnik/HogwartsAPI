package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.teacher.Teacher;
import com.bdev.hogwarts_api.rest_service.teacher.TeacherRestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@Api(tags = "Teacher", description = "PROTECTED")
public class TeacherRest extends CommonRest {
    @Autowired
    private TeacherRestService teacherRestService;

    @GetMapping("")
    public List<Teacher> getAllTeachers(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    ) {
        return teacherRestService.getAllTeachers(
                getUserInfo(authToken)
        );
    }

    @GetMapping("/id/{id}")
    public Teacher getTeacher(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long id
    ) {
        return teacherRestService.getTeacherById(
                getUserInfo(authToken),
                id
        );
    }

    @GetMapping("/login/{login}")
    public Teacher getTeacherByLogin(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable String login
    ) {
        return teacherRestService.getTeacherByLogin(
                getUserInfo(authToken),
                login
        );
    }

    @PostMapping("")
    public long createTeacher(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody Teacher teacher
    ) {
        return teacherRestService.createTeacher(
                getUserInfo(authToken),
                teacher
        );
    }

    @PutMapping("")
    public void editTeacher(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody Teacher teacher
    ) {
        teacherRestService.editTeacher(
                getUserInfo(authToken),
                teacher
        );
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long id
    ) {
        teacherRestService.deleteTeacher(
                getUserInfo(authToken),
                id
        );
    }
}
