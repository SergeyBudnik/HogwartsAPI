package com.bdev.hogwarts_api.rest.admin.student

import com.bdev.hogwarts_api.data.dto.student.studying.Student
import com.bdev.hogwarts_api.rest.admin.AdminCommonCRUDRest
import com.bdev.hogwarts_api.rest_service.admin.student.AdminStudentRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/students/management")
@Api(tags = ["Admin Students Management"], description = "PROTECTED")
class AdminStudentRest @Autowired constructor(
        studentRestService: AdminStudentRestService
) : AdminCommonCRUDRest<String, Student>(studentRestService) {
    @GetMapping("")
    override fun getAll(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): ResponseEntity<Any> = super.getAll(authToken)

    @GetMapping("/{id}")
    override fun getById(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: String
    ): ResponseEntity<Any> = super.getById(authToken, id)

    @PostMapping("")
    override fun create(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody o: Student
    ): ResponseEntity<Any> = super.create(authToken, o)

    @PutMapping("")
    override fun update(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody o: Student
    ): ResponseEntity<Any> = super.update(authToken, o)

    @DeleteMapping("/{id}")
    override fun delete(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: String
    ): ResponseEntity<Any> = super.delete(authToken, id)
}
