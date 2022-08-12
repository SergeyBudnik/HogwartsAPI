package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.lesson_type.LessonTypeModel
import org.springframework.data.jpa.repository.JpaRepository

interface LessonTypeDao : JpaRepository<LessonTypeModel, String>
