package com.bdev.hogwarts_api.data.converter.lesson_transfer

import com.bdev.hogwarts_api.data.dto.lesson.LessonTransfer
import com.bdev.hogwarts_api.data.model.lesson_transfer.LessonTransferModel

object LessonTransferDtoConverter {
    fun convert(lessonTransfer: LessonTransfer): LessonTransferModel {
        val lessonTransferModel = LessonTransferModel()

        lessonTransferModel.id = lessonTransfer.id
        lessonTransferModel.lessonId = lessonTransfer.lessonId
        lessonTransferModel.teacherId = lessonTransfer.teacherId
        lessonTransferModel.fromTime = lessonTransfer.fromTime
        lessonTransferModel.toTime = lessonTransfer.toTime

        return lessonTransferModel
    }
}
