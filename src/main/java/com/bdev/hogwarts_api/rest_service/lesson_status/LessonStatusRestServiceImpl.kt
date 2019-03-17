package com.bdev.hogwarts_api.rest_service.lesson_status

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.lesson.LessonStatus
import com.bdev.hogwarts_api.service.lesson_status.LessonStatusService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class LessonStatusRestServiceImpl : LessonStatusRestService {
    @Autowired
    private lateinit var lessonStatusService: LessonStatusService

    @Transactional(readOnly = true)
    override fun getLessonsStatuses(
            userInfo: MunicipaliUserInfo,
            from: Long,
            to: Long
    ): List<LessonStatus> {
        return lessonStatusService.getLessonsStatuses(from, to)
    }

    @Transactional
    override fun addLessonStatus(
            userInfo: MunicipaliUserInfo,
            lessonStatus: LessonStatus
    ): Long {
        return lessonStatusService.addLessonStatus(lessonStatus)
    }
}
