package com.bdev.hogwarts_api.service.lesson_status

import com.bdev.hogwarts_api.dao.LessonStatusDao
import com.bdev.hogwarts_api.data.converter.lesson_status.LessonStatusDtoConverter
import com.bdev.hogwarts_api.data.converter.lesson_status.LessonStatusModelConverter
import com.bdev.hogwarts_api.data.dto.lesson.LessonStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LessonStatusServiceImpl @Autowired constructor(
    private val lessonStatusDao: LessonStatusDao
): LessonStatusService {
    override fun getLessonsStatuses(from: Long, to: Long): List<LessonStatus> {
        return lessonStatusDao
                .getAllByActionTimeGreaterThanAndActionTimeLessThan(from, to)
                .map { LessonStatusModelConverter.convert(it) }
    }

    override fun getLessonStatus(lessonId: Long, lessonActionTime: Long): LessonStatus? {
        return lessonStatusDao
            .getAllByLessonIdAndActionTime(lessonId = lessonId, actionTime = lessonActionTime)
            .maxByOrNull { lessonStatusModel -> lessonStatusModel.id!! }
            ?.let { lessonStatusModel -> LessonStatusModelConverter.convert(lessonStatusModel) }
    }

    override fun addLessonStatus(lessonStatus: LessonStatus): Long {
        if (lessonStatus.id != null) {
            throw RuntimeException()
        }

        return lessonStatusDao
            .save(LessonStatusDtoConverter.convert(lessonStatus))
            .id ?: throw RuntimeException()
    }
}
