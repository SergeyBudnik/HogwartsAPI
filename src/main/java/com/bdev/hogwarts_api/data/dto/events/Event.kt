package com.bdev.hogwarts_api.data.dto.events

import com.bdev.hogwarts_api.data.dto.common.LessonTime

class Event(
    var id: Long? = null,
    var eventType: EventType = EventType.OPEN_LESSON,
    var name: String = "",
    var cabinetId: Long = 0,
    var teacherId: Long = 0,
    var date: Long = 0,
    var startTime: LessonTime = LessonTime.T_07_00,
    var finishTime: LessonTime = LessonTime.T_07_00
)
