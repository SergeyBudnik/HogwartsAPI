package com.bdev.hogwarts_api.data.dto.student

import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.EducationLevel

class Student(
    val id: Long? = null,
    val groupIds: List<Long> = emptyList(),
    val name: String = "",
    val statusType: StudentStatusType = StudentStatusType.STUDYING,
    val phones: List<String> = emptyList(),
    val emails: List<String> = emptyList(),
    val educationLevel: EducationLevel = EducationLevel.BEGINNER,
    val age: Age = Age.ADULT,
    val referralSource: StudentReferralSource = StudentReferralSource.UNKNOWN
)