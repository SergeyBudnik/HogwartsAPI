package com.bdev.hogwarts_api.rest

import com.bdev.hogwarts_api.data.dto.student.Student
import com.bdev.hogwarts_api.rest_service.student.StudentRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
@Api(tags = ["Student"], description = "PROTECTED")
class StudentRest : CommonRest() {
    @Autowired
    private lateinit var studentRestService: StudentRestService

    @GetMapping("")
    fun getAllStudents(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<Student> {
        return studentRestService.getAllStudents(
                getUserInfo(authToken)
        )
    }

    @GetMapping("/{studentId}")
    fun getStudentById(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentId: Long
    ): Student {
        return studentRestService.getStudentById(
                getUserInfo(authToken),
                studentId
        )
    }

    @PostMapping("")
    fun createStudent(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody student: Student
    ): Long {
        return studentRestService.createStudent(
                getUserInfo(authToken),
                student
        )
    }

    @PutMapping("")
    fun editStudent(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody student: Student
    ) {
        studentRestService.editStudent(
                getUserInfo(authToken),
                student
        )
    }

    @DeleteMapping("/{studentId}")
    fun deleteStudent(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentId: Long
    ) {
        studentRestService.deleteStudent(
                getUserInfo(authToken),
                studentId
        )
    }
}
