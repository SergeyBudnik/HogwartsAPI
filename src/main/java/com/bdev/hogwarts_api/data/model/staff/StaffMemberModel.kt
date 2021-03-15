package com.bdev.hogwarts_api.data.model.staff

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "HG_STAFF_MEMBER")
open class StaffMemberModel {
    @Id
    @Column(name = "LOGIN", length = 128, nullable = false)
    var login: String = ""

    @Column(name = "PERSON_ID", nullable = false)
    var personId: Long = 0L

    @Column(name = "ACTIVE")
    var active: Boolean = false

    @Column(name = "SALARY_IN_30_M", nullable = false)
    var salaryIn30m: Int = 0

    @Column(name = "ROLE_TEACHER")
    var roleTeacher: Boolean = false

    @Column(name = "SUBS_FREE_LESSON_REQUEST")
    var subscribeToFreeLessonRequest: Boolean = false
}
