package com.bdev.hogwarts_api.data.dto.teacher

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.common.LessonTime

class TeacherAvailability(
        var dayOfWeek: DayOfWeek = DayOfWeek.MONDAY,
        val time: LessonTime = LessonTime.T_07_00
)
