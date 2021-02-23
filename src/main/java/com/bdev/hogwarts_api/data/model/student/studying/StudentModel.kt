package com.bdev.hogwarts_api.data.model.student.studying

import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.EducationLevel
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType
import javax.persistence.*

@Entity
@Table(name = "HG_STUDENT_2")
open class StudentModel constructor(
        @Id
        @Column(name = "LOGIN", length = 128, nullable = false)
        var login: String,

        @Column(name = "PERSON_ID", nullable = false)
        var personId: Long,

        @Column(name = "MANAGER_LOGIN", nullable = false)
        var managerLogin: String,

        @Column(name = "EDUCATION_AGE", nullable = false)
        var educationAge: String,
        @Column(name = "EDUCATION_LEVEL", nullable = false)
        var educationLevel: String,

        @Column(name = "STATUS_TYPE", nullable = false)
        var statusType: String,

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = [CascadeType.ALL], orphanRemoval = true)
        var studentGroups: MutableList<StudentGroupReferenceModel> = ArrayList()
) {
    constructor(): this(
            login = "",
            personId = 0L,
            managerLogin = "",
            educationAge = Age.UNKNOWN.id,
            educationLevel = EducationLevel.UNKNOWN.id,
            statusType = StudentStatusType.STUDYING.id,
            studentGroups = ArrayList()
    )
}
