package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.lesson_status.LessonStatusModel
import org.springframework.data.jpa.repository.JpaRepository

interface LessonStatusDao : JpaRepository<LessonStatusModel, Long> {
    fun getAllByActionTimeGreaterThanAndActionTimeLessThan(from: Long, to: Long): List<LessonStatusModel>
}
