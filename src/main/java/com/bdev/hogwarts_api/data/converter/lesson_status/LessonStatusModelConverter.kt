package com.bdev.hogwarts_api.data.converter.lesson_status

import com.bdev.hogwarts_api.data.dto.lesson.LessonStatus
import com.bdev.hogwarts_api.data.model.lesson_status.LessonStatusModel

object LessonStatusModelConverter {
    fun convert(lessonStatusModel: LessonStatusModel): LessonStatus {
        return LessonStatus(
                id = lessonStatusModel.id,
                lessonId =  lessonStatusModel.lessonId,
                type = lessonStatusModel.type,
                actionTime = lessonStatusModel.actionTime,
                creationTime = lessonStatusModel.creationTime
        )
    }
}
