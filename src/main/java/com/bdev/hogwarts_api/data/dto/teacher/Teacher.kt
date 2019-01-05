package com.bdev.hogwarts_api.data.dto.teacher

class Teacher(
    var id: Long? = null,
    var login: String = "",
    var name: String = "",
    var type: TeacherType = TeacherType.NON_NATIVE,
    var phones: List<String> = emptyList(),
    var emails: List<String> = emptyList(),
    var availability: List<TeacherAvailability> = emptyList()
)