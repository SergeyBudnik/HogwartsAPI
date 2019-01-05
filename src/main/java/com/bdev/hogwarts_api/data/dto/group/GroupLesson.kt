package com.bdev.hogwarts_api.data.dto.group

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.common.LessonTime

class GroupLesson(
    var id: Long? = null,
    var day: DayOfWeek = DayOfWeek.MONDAY,
    var startTime: LessonTime? = LessonTime.T_07_00,
    var finishTime: LessonTime = LessonTime.T_07_00,
    var teacherId: Long = 0
)
