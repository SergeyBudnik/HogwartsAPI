package com.bdev.hogwarts_api.data.dto.lesson

enum class LessonStatusType {
    CANCELED, MOVED, FINISHED
}

class LessonStatus(
        val id: Long? = null,
        val lessonId: Long = 0L,
        val type: LessonStatusType = LessonStatusType.FINISHED,
        val actionTime: Long = 0L,
        val creationTime: Long = 0L
)
