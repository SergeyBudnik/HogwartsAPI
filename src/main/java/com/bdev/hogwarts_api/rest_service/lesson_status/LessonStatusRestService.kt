package com.bdev.hogwarts_api.rest_service.lesson_status

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.lesson.LessonStatus

interface LessonStatusRestService {
    fun getLessonsStatuses(userInfo: MunicipaliUserInfo, from: Long, to: Long): List<LessonStatus>
    fun addLessonStatus(userInfo: MunicipaliUserInfo, lessonStatus: LessonStatus): Long
}
