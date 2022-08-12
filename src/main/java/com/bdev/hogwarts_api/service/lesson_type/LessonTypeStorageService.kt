package com.bdev.hogwarts_api.service.lesson_type

import com.bdev.hogwarts_api.dao.LessonTypeDao
import com.bdev.hogwarts_api.data.converter.lesson_type.LessonTypeDtoConverter
import com.bdev.hogwarts_api.data.converter.lesson_type.LessonTypeModelConverter
import com.bdev.hogwarts_api.data.dto.lesson_type.LessonType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface LessonTypeStorageService {
    fun exists(id: String): Boolean
    fun getAll(): List<LessonType>
    fun set(lessonType: LessonType)
    fun delete(id: String): Boolean
}

@Service
class LessonTypeStorageServiceImpl @Autowired constructor(
    private val lessonTypeDao: LessonTypeDao
): LessonTypeStorageService {
    override fun exists(id: String): Boolean {
        return lessonTypeDao.exists(id)
    }

    override fun getAll(): List<LessonType> {
        return lessonTypeDao
            .findAll()
            .map { model -> LessonTypeModelConverter.convert(model = model) }
    }

    override fun set(lessonType: LessonType) {
        lessonTypeDao.save(
            LessonTypeDtoConverter.convert(
                dto = lessonType
            )
        )
    }

    override fun delete(id: String): Boolean {
        return if (lessonTypeDao.exists(id)) {
            lessonTypeDao.delete(id)

            true
        } else {
            false
        }
    }
}
