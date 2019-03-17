package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.teacher.Teacher
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.teacher.TeacherRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/teachers")
@Api(tags = ["Teacher"], description = "PROTECTED")
class AdminTeacherRest : CommonRest() {
    @Autowired
    private lateinit var teacherRestService: TeacherRestService

    @GetMapping("")
    fun getAllTeachers(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<Teacher> {
        return teacherRestService.getAllTeachers(
                getUserInfo(authToken)
        )
    }

    @GetMapping("/id/{id}")
    fun getTeacher(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: Long
    ): Teacher {
        return teacherRestService.getTeacherById(
                getUserInfo(authToken),
                id
        )
    }

    @GetMapping("/login/{login}")
    fun getTeacherByLogin(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable login: String
    ): Teacher {
        return teacherRestService.getTeacherByLogin(
                getUserInfo(authToken),
                login
        )
    }

    @PostMapping("")
    fun createTeacher(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody teacher: Teacher
    ): Long {
        return teacherRestService.createTeacher(
                getUserInfo(authToken),
                teacher
        )
    }

    @PutMapping("")
    fun editTeacher(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody teacher: Teacher
    ) {
        teacherRestService.editTeacher(
                getUserInfo(authToken),
                teacher
        )
    }

    @DeleteMapping("/{id}")
    fun deleteTeacher(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: Long
    ) {
        teacherRestService.deleteTeacher(
                getUserInfo(authToken),
                id
        )
    }
}
