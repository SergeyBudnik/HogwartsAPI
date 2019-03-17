package com.bdev.hogwarts_api.data.converter.lesson_status

import com.bdev.hogwarts_api.data.dto.lesson.LessonStatus
import com.bdev.hogwarts_api.data.model.lesson_status.LessonStatusModel

object LessonStatusDtoConverter {
    fun convert(lessonStatus: LessonStatus): LessonStatusModel {
        val lessonStatusModel = LessonStatusModel()

        lessonStatusModel.id = lessonStatus.id
        lessonStatusModel.lessonId = lessonStatus.lessonId
        lessonStatusModel.type = lessonStatus.type
        lessonStatusModel.actionTime = lessonStatus.actionTime
        lessonStatusModel.creationTime = lessonStatus.creationTime

        return lessonStatusModel
    }
}
