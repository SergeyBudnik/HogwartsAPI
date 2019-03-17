package com.bdev.hogwarts_api.service.lesson_transfer

import com.bdev.hogwarts_api.dao.LessonTransferDao
import com.bdev.hogwarts_api.data.converter.lesson_transfer.LessonTransferModelConverter
import com.bdev.hogwarts_api.data.dto.lesson.LessonTransfer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class LessonTransferServiceImpl : LessonTransferService {
    @Autowired
    private lateinit var lessonTransferDao: LessonTransferDao

    override fun getAllLessonsTransfers(): List<LessonTransfer> {
        return lessonTransferDao.findAll().map { LessonTransferModelConverter.convert(it) }
    }
}
