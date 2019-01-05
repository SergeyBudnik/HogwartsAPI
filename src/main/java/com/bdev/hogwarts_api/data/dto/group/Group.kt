package com.bdev.hogwarts_api.data.dto.group

import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.EducationLevel

class Group(
    var id: Long? = null,
    var bookName: String = "",
    var type: GroupType = GroupType.GROUP,
    var cabinetId: Long = 0,
    var managerId: Long = 0,
    var lessons: List<GroupLesson> = emptyList(),
    var age: Age = Age.ADULT,
    var educationLevel: EducationLevel = EducationLevel.BEGINNER,
    var color: String = "ffffff"
)
