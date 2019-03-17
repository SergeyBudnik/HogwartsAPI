package com.bdev.hogwarts_api.service.lesson_status

import com.bdev.hogwarts_api.data.dto.lesson.LessonStatus

interface LessonStatusService {
    fun getLessonsStatuses(from: Long, to: Long): List<LessonStatus>
    fun addLessonStatus(lessonStatus: LessonStatus): Long
}
