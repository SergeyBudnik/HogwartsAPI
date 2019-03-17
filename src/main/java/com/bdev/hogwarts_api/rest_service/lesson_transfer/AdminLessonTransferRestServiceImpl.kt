package com.bdev.hogwarts_api.rest_service.lesson_transfer

import com.bdev.hogwarts_api.data.dto.lesson.LessonTransfer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class AdminLessonTransferRestServiceImpl : AdminLessonTransferRestService {
    @Transactional(readOnly = true)
    override fun getAllLessonsTransfers(): List<LessonTransfer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
