package com.bdev.hogwarts_api.rest_service.lesson_transfer

import com.bdev.hogwarts_api.data.dto.lesson.LessonTransfer

interface AdminLessonTransferRestService {
    fun getAllLessonsTransfers(): List<LessonTransfer>
}
