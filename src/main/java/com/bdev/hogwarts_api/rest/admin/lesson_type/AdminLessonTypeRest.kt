package com.bdev.hogwarts_api.rest.admin.lesson_type

import com.bdev.hogwarts_api.data.dto.lesson_type.LessonType
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.lesson_type.AdminLessonTypeRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/lesson-types")
@Api(tags = ["Admin Lesson Types"], description = "PROTECTED")
class AdminLessonTypeRest @Autowired constructor(
    private val adminLessonTypeRestService: AdminLessonTypeRestService
): CommonRest() {
    @GetMapping(value = ["/"])
    fun getAll(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): ResponseEntity<*> {
        return adminLessonTypeRestService.getAll(
            userInfo = getUserInfo(authToken = authToken)
        )
    }

    @PostMapping(value = ["/"])
    fun set(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @RequestBody lessonType: LessonType
    ): ResponseEntity<*> {
        return adminLessonTypeRestService.set(
            userInfo = getUserInfo(authToken = authToken),
            lessonType = lessonType
        )
    }

    @DeleteMapping(value = ["/"])
    fun delete(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @RequestBody lessonTypeId: String
    ): ResponseEntity<*> {
        return adminLessonTypeRestService.delete(
            userInfo = getUserInfo(authToken = authToken),
            id = lessonTypeId
        )
    }
}
