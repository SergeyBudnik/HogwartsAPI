package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.lesson.LessonStatus
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.lesson_status.LessonStatusRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/lesson-status")
@Api(tags = ["Lesson Status"], description = "PROTECTED")
class AdminLessonStatusRest : CommonRest() {
    @Autowired
    private lateinit var lessonStatusRestService: LessonStatusRestService

    @GetMapping("/{from}/{to}")
    fun getLessonStatuses(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable from: Long,
            @PathVariable to: Long
    ): List<LessonStatus> {
        return lessonStatusRestService.getLessonsStatuses(
                getUserInfo(authToken), from, to
        )
    }

    @PostMapping("")
    fun addLessonStatus(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody lessonStatus: LessonStatus
    ): Long {
        return lessonStatusRestService.addLessonStatus(
                getUserInfo(authToken), lessonStatus
        )
    }
}
