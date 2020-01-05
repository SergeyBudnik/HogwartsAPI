package com.bdev.hogwarts_api.data.model.student.on_boarding

import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.EducationLevel
import com.bdev.hogwarts_api.data.dto.student.on_boarding.StudentOnBoardingResultType
import javax.persistence.*
import kotlin.math.log

@Entity
@Table(name = "HG_STUDENT_ON_BOARDING")
open class StudentOnBoardingModel constructor(
        @Id
        @Column(name = "LOGIN", length = 128, nullable = false)
        var login: String,

        @Column(name = "PERSON_ID", nullable = false)
        var personId: Long,

        @Column(name = "EDUCATION_AGE", nullable = false)
        var educationAge: String,
        @Column(name = "EDUCATION_LEVEL", nullable = false)
        var educationLevel: String,

        @Column(name = "MANAGER_LOGIN", nullable = false)
        var managerLogin: String,

        @Column(name = "RESULT_TYPE", nullable = false)
        var resultType: String,
        @Column(name = "RESULT_COMMENT", nullable = true)
        var resultComment: String?,

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentOnBoarding", cascade = [CascadeType.ALL], orphanRemoval = true)
        var actions: MutableList<StudentOnBoardingActionModel>
) {
    constructor(): this(
            login = "",
            personId = 0L,
            educationAge = Age.UNKNOWN.id,
            educationLevel = EducationLevel.UNKNOWN.id,
            managerLogin = "",
            resultType = StudentOnBoardingResultType.PROGRESS.id,
            resultComment = null,
            actions = ArrayList()
    )
}
