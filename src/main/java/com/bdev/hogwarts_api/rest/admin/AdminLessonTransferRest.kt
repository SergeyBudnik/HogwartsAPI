package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.lesson.LessonTransfer
import com.bdev.hogwarts_api.rest_service.lesson_transfer.AdminLessonTransferRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lesson-transfer")
@Api(tags = ["Lesson Transfer"], description = "PROTECTED")
class AdminLessonTransferRest {
    @Autowired
    lateinit var adminLessonTransferRestService: AdminLessonTransferRestService

    @GetMapping("")
    fun getAllLessonsTransfers(): List<LessonTransfer> {
        return adminLessonTransferRestService.getAllLessonsTransfers()
    }
}
