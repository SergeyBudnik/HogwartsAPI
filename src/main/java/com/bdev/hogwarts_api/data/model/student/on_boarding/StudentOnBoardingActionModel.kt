package com.bdev.hogwarts_api.data.model.student.on_boarding

import javax.persistence.*

@Entity
@Table(name = "HG_STUDENT_ON_BOARDING_ACTION")
open class StudentOnBoardingActionModel constructor(
        @Id
        @Column(name = "ID")
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "STUDENT_ON_BOARDING_ID", nullable = false)
        var studentOnBoarding: StudentOnBoardingModel,

        @Column(name = "ASSIGNEE_LOGIN", nullable = false)
        var assigneeLogin: String,
        @Column(name = "ACTION_TIME", nullable = false)
        var actionTime: Long,
        @Column(name = "DESCRIPTION", nullable = false)
        var description: String,
        @Column(name = "CREATION_TIME", nullable = false)
        var creationTime: Long,
        @Column(name = "COMPLETION_TIME", nullable = true)
        var completionTime: Long?
) {
    @Suppress("unused")
    constructor(): this(
            studentOnBoarding = StudentOnBoardingModel(),
            assigneeLogin = "",
            actionTime = 0L,
            description = "",
            creationTime = 0L,
            completionTime = null
    )
}
