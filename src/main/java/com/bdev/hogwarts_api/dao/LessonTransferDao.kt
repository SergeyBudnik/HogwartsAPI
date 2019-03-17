package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.lesson_transfer.LessonTransferModel
import org.springframework.data.jpa.repository.JpaRepository

interface LessonTransferDao : JpaRepository<LessonTransferModel, Long>
