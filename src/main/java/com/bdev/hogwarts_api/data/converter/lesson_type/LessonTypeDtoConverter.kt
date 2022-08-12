package com.bdev.hogwarts_api.data.converter.lesson_type

import com.bdev.hogwarts_api.data.dto.lesson_type.LessonType
import com.bdev.hogwarts_api.data.model.lesson_type.LessonTypeModel

object LessonTypeDtoConverter {
    fun convert(dto: LessonType): LessonTypeModel =
        LessonTypeModel(
            id = dto.id,
            name = dto.name
        )
}
