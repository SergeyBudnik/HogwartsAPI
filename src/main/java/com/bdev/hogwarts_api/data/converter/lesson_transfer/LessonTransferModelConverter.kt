package com.bdev.hogwarts_api.data.converter.lesson_transfer

import com.bdev.hogwarts_api.data.dto.lesson.LessonTransfer
import com.bdev.hogwarts_api.data.model.lesson_transfer.LessonTransferModel

object LessonTransferModelConverter {
    fun convert(lessonTransferModel: LessonTransferModel): LessonTransfer {
        return LessonTransfer(
                id = lessonTransferModel.id,
                lessonId = lessonTransferModel.lessonId,
                teacherId = lessonTransferModel.teacherId,
                fromTime = lessonTransferModel.fromTime,
                toTime = lessonTransferModel.toTime
        )
    }
}
