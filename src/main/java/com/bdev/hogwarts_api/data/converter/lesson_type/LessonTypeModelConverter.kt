package com.bdev.hogwarts_api.data.converter.lesson_type

import com.bdev.hogwarts_api.data.dto.lesson_type.LessonType
import com.bdev.hogwarts_api.data.model.lesson_type.LessonTypeModel

object LessonTypeModelConverter {
    fun convert(model: LessonTypeModel): LessonType =
        LessonType(
            id = model.id,
            name = model.name
        )
}
