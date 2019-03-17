package com.bdev.hogwarts_api.service.lesson_transfer

import com.bdev.hogwarts_api.data.dto.lesson.LessonTransfer

interface LessonTransferService {
    fun getAllLessonsTransfers(): List<LessonTransfer>
}
