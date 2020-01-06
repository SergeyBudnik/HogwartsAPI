package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.student.StudentLegacy
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.student_legacy.StudentRestServiceLegacy
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
@Api(tags = ["Student"], description = "PROTECTED")
class AdminStudentRestLegacy : CommonRest() {
    @Autowired
    private lateinit var studentRestService: StudentRestServiceLegacy

    @GetMapping("")
    fun getAllStudents(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<StudentLegacy> {
        return studentRestService.getAllStudents(
                getUserInfo(authToken)
        )
    }

    @GetMapping("/{studentId}")
    fun getStudentById(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentId: Long
    ): StudentLegacy {
        return studentRestService.getStudentById(
                getUserInfo(authToken),
                studentId
        )
    }

    @PostMapping("")
    fun createStudent(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody student: StudentLegacy
    ): Long {
        return studentRestService.createStudent(
                getUserInfo(authToken),
                student
        )
    }

    @PutMapping("")
    fun editStudent(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody student: StudentLegacy
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
